class Solution {
    
    //har value ke index ko preserve krna h further unke smaller count ko
    //unke index pe store kaarane ke liye jaise [5,2,6,1],Output: [2,1,1,0] me
    // 5 ke index pe hi uske baad ke smaller numbers ka count store krna h isiliye class pair bana rhe
    class Pair {
        int val, idx;
        Pair(int v, int i){
            val = v;
            idx = i;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        //nums ko pair me store kra rhey with each element with its index as pair
        for(int i=0;i<n;i++)
            arr[i] = new Pair(nums[i], i);

        //this stores count smaller number right of the element at its index
        int[] ans = new int[n];
        mergeSort(arr, 0, n-1, ans);

        List<Integer> res = new ArrayList<>();
        for(int x : ans) res.add(x);
        return res;
    }

    void mergeSort(Pair[] arr, int low, int high, int[] ans){
        if(low >= high) return;

        int mid = (low+high)/2;
        mergeSort(arr, low, mid, ans);
        mergeSort(arr, mid+1, high, ans);
        merge(arr, low, mid, high, ans);
    }

    void merge(Pair[] arr, int low, int mid, int high, int[] ans){
        List<Pair> temp = new ArrayList<>();
        int left = low;
        int right = mid+1;
        int rightCount = 0;

        while(left <= mid && right <= high){
            if(arr[left].val <= arr[right].val){
                ans[arr[left].idx] += rightCount;
                temp.add(arr[left++]);
            }
            else{
                rightCount++;
                temp.add(arr[right++]);
            }
        }

        while(left <= mid){
            ans[arr[left].idx] += rightCount;
            temp.add(arr[left++]);
        }

        while(right <= high){
            temp.add(arr[right++]);
        }

        for(int i=low;i<=high;i++)
            arr[i] = temp.get(i-low);
    }
}
