class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        HashMap<Integer, List<String>> wordMap = new HashMap<>();
        return helper(0, s, wordSet, wordMap);
    }

    private List<String>  helper(int start, String s, HashSet<String> wordSet, HashMap<Integer, List<String>>wordMap){
        if(wordMap.containsKey(start)) return wordMap.get(start);

        ArrayList<String> res = new ArrayList<>();

        if(start == s.length()){
            res.add("");
            return res;
        }

        for(int end = start + 1; end <= s.length(); end++){
            List<String> subList = new ArrayList<>();
            String word = s.substring(start, end);

            if(wordSet.contains(word)){
                subList = helper(end, s, wordSet, wordMap);

                for(String sub : subList){
                    if(sub == ""){
                        res.add(word);
                    }else{
                        res.add(word+ " " +sub);
                    }
                }
            }
        }
        wordMap.put(start, res);
        return res;
    }
}