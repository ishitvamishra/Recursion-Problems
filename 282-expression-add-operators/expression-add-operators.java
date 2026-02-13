class Solution {
    private void dfs(String num, int target, int start, long currVal, long lastOp, String exp, List<String> res){
        if(start == num.length()){
            if(currVal == target){
                res.add(exp);
            }
            return;
        }

        for(int i = start; i < num.length(); i++){
            if(i > start && num.charAt(start) == '0') return;

            String currNum = num.substring(start, i+1);
            long currNumVal = Long.parseLong(currNum);

            if(start == 0){
                dfs(num, target, i+1, currNumVal, currNumVal, currNum, res);
            }

            else{
                //add (+)
                dfs(num, target, i+1, currVal+currNumVal, currNumVal, exp+ "+" +currNum, res);

                //add(-)
                dfs(num, target, i+1, currVal-currNumVal, -currNumVal, exp+ "-" +currNum, res);

                //add(*) precendence wise
                dfs(num, target, i+1, currVal-lastOp + lastOp*currNumVal, lastOp*currNumVal, exp+ "*" +currNum, res);
            }
        }
    }
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", res);
        return res;
    }
}