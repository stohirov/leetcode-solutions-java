public class Solution1095 {

  public int findInMountainArray(int target, MountainArray mountainArr) {
    int n = mountainArr.length();

    int low = 0;
    int high = n - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    int peak = low;

    int left = ascendingSearch(target, mountainArr, 0, peak);
    if (left != -1) {
      return left;
    }
    return descendingSearch(target, mountainArr, peak + 1, n - 1);
  }

  private int ascendingSearch(int target, MountainArray arr, int low, int high) {
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int value = arr.get(mid);
      if (value == target) {
        return mid;
      } else if (value < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  private int descendingSearch(int target, MountainArray arr, int low, int high) {
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int value = arr.get(mid);
      if (value == target) {
        return mid;
      } else if (value > target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return -1;
  }

  interface MountainArray {
    int get(int index);

    int length();
  }

  static class ArrayMountain implements MountainArray {
    private final int[] data;

    ArrayMountain(int[] data) {
      this.data = data;
    }

    public int get(int index) {
      return data[index];
    }

    public int length() {
      return data.length;
    }
  }
}
