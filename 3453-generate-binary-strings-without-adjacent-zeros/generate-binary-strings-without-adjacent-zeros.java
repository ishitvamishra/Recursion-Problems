class Solution {
    public List<String> validStrings(int n) {
        List<String> res = new ArrayList<>();
        generate(n, "", res);
        return res;
    }

    private void generate(int n, String curr, List<String> res){
        if(curr.length() == n){
            res.add(curr);
            return;
        }

        generate(n, curr+"1", res);

        if(curr.length() == 0 || curr.charAt(curr.length() - 1) != '0'){
            generate(n, curr+"0", res);
        }
    }
}