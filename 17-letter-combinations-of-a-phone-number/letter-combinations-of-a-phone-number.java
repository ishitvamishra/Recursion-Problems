class Solution {
    private final String[] map;

    Solution(){
        map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    }

    public void helper(String digits, List<String> ans, int ind, String current){
        if(ind == digits.length()){
            ans.add(current);
            return;
        }

        String s = map[digits.charAt(ind)-'0'];
        for(int i = 0; i < s.length(); i++){
            helper(digits, ans, ind+1, current+s.charAt(i));
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length() == 0) return ans;

        helper(digits, ans, 0, "");
        return ans;
    }
}