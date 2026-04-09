#!/bin/bash

# Check argument
if [ "$#" -lt 1 ]; then
  echo "Usage: ./move.sh <TARGET_FOLDER>"
  exit 1
fi

TARGET="$1"

OWNER="ayushworkk07"
REPO="Striver-190"
BRANCH="main"

echo "Moving files into LeetCode/$TARGET/ ..."

COMMIT_SHA=$(gh api repos/$OWNER/$REPO/git/ref/heads/$BRANCH --jq '.object.sha')
TREE_SHA=$(gh api repos/$OWNER/$REPO/git/commits/$COMMIT_SHA --jq '.tree.sha')
TREE_JSON=$(gh api "repos/$OWNER/$REPO/git/trees/$TREE_SHA?recursive=1")

NEW_TREE=$(echo "$TREE_JSON" | jq --arg target "$TARGET" '[
  .tree[] |
  select(.type == "blob") |
  select(
    (.path | startswith("LeetCode/Easy/")) or
    (.path | startswith("LeetCode/Medium/")) or
    (.path | startswith("LeetCode/Hard/"))
  ) |
  [
    { path: ("LeetCode/" + $target + "/" + .path[9:]), mode: .mode, type: .type, sha: .sha },
    { path: .path, mode: .mode, type: .type, sha: null }
  ]
] | flatten')

COUNT=$(echo "$NEW_TREE" | jq '[.[] | select(.sha != null)] | length')
echo "Files to move: $COUNT"

NEW_TREE_SHA=$(jq -n --arg base_tree "$TREE_SHA" --argjson tree "$NEW_TREE" \
  '{base_tree: $base_tree, tree: $tree}' | \
  gh api repos/$OWNER/$REPO/git/trees --method POST --input - --jq '.sha')

COMMIT_MSG="refactor: move problems under $TARGET/

Co-Authored-By: Oz <oz-agent@warp.dev>"

NEW_COMMIT_SHA=$(jq -n \
  --arg message "$COMMIT_MSG" \
  --arg tree "$NEW_TREE_SHA" \
  --arg parent "$COMMIT_SHA" \
  '{message: $message, tree: $tree, parents: [$parent]}' | \
  gh api repos/$OWNER/$REPO/git/commits --method POST --input - --jq '.sha')

jq -n --arg sha "$NEW_COMMIT_SHA" '{sha: $sha}' | \
  gh api repos/$OWNER/$REPO/git/refs/heads/$BRANCH --method PATCH --input - > /dev/null

echo "Done! https://github.com/$OWNER/$REPO/tree/main/LeetCode/$TARGET"