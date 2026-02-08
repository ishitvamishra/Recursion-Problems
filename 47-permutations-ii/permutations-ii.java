class Solution {
    private void permutation(int index, int[] nums, List<List<Integer>> ans){
        if(index == nums.length){
            List<Integer> ds = new ArrayList<>();
            for(int x : nums) ds.add(x);
            ans.add(ds);
            return;
        }

        HashSet<Integer> used = new HashSet<>();
        //basically yaha pe ye condition(if(i > index && nums[i] == nums[i-1]) continue), jise hum subset me use kr rhey they, isiliye kaam nhi kr rhi kyuki same level pe ek swap ke baad array sorted nhi bacha toh duplicate elemment is no longer adjacent isiliye condition check ko bypass kr jaarha aur subset me swapping nhi hoti thi hai toh array hmesha sorted rehta hai baaki code permutation wala hi hai

        for(int i = index; i < nums.length; i++){
            if(used.contains(nums[i])) continue;
            used.add(nums[i]);

            swap(i, index, nums);
            permutation(index + 1, nums, ans);
            swap(index, i, nums);
        }
    }

    private void swap(int i, int j, int[] nums){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permutation(0, nums, ans);
        return ans;
    }
}
