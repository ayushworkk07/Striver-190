class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();

        partitionRecursive(s , 0 , new ArrayList<String>() ,ans );
        return ans;
    }

    public void partitionRecursive(String str , int index , ArrayList<String> currList ,  List<List<String>> ans){
        if(index == str.length()){
            ans.add(new ArrayList<>(currList));
            return;
        }
        for(int i = index ; i < str.length() ; i++){
            if(checkPalindrom(str,index,i)){
                currList.add(str.substring(index,i+1));
                partitionRecursive(str,i+1,currList,ans);
                currList.remove(currList.size()-1);
            }
        }
    }

    public boolean checkPalindrom(String str , int start ,int end){
        while(start >= 0 && end < str.length() && start <= end){
            if(str.charAt(start)!=str.charAt(end))
                return false;

            start++;
            end--;
        }
        return true;
    }
}