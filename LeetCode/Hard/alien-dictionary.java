/*  find herarchy of characters by comparing i , i+1 strings 
and make an edge between them
    -> run topological sort to see if there is no cyclic dependency
    return the string made by topo sort
*/
class Solution {
    public String findOrder(String[] words) {
        int N = words.length; 
        int K = 26;
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) adj.add(new ArrayList<>());
        
        int[] indegree = new int[K];
        Arrays.fill(indegree, -1); // Mark all as "not present"

        // 0. Mark letters that exist in the dictionary
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (indegree[c - 'a'] == -1) indegree[c - 'a'] = 0;
            }
        }

        // 1. Build the Graph
        for (int i = 0; i < N - 1; i++) {
            String s1 = words[i];
            String s2 = words[i + 1];
            
            // Edge Case: Prefix check (["abc", "ab"] is invalid)
            if (s1.length() > s2.length() && s1.startsWith(s2)) return "";

            int len = Math.min(s1.length(), s2.length());
            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    int u = s1.charAt(j) - 'a';
                    int v = s2.charAt(j) - 'a';
                    adj.get(u).add(v);
                    indegree[v]++;
                    break; 
                }
            }
        }

        // 2. Kahn's Algorithm
        Queue<Integer> q = new LinkedList<>();
        int totalChars = 0;
        for (int i = 0; i < K; i++) {
            if (indegree[i] == 0) q.add(i);
            if (indegree[i] != -1) totalChars++; // Count unique chars
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append((char)(curr + 'a'));

            for (int neighbor : adj.get(curr)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        // 3. Cycle Check
        // If sb length != total unique chars, there's a cycle
        return sb.length() == totalChars ? sb.toString() : "";
    }
}


