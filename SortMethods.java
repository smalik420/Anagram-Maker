import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Sidhant Malik
 *	@since	December 4, 2022
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(List<String> arr) {
		for (int outer = arr.size() - 1; outer > 0; outer--) {
			for (int inner = 0; inner < outer; inner++) {
				if (arr.get(inner).compareTo(arr.get(inner + 1)) > 0)
					swap(arr, inner, inner + 1);
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<String> arr, int x, int y) {
		String temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(List<String> arr) {
		for (int i = 0; i < arr.size(); i++) {
			int max = 0;
			for (int j = 1; j < arr.size() - i; j++) {
				if (arr.get(j).compareTo(arr.get(max)) > 0)
					max = j;
			}
			swap(arr, max, arr.size() - i - 1);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(List<String> arr) {
		for (int i = 0; i < arr.size() - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
					if (arr.get(j).compareTo(arr.get(j - 1)) < 0)
						swap(arr, j, j - 1);
					else
						j = 0;
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(List<String> arr) {
		sort(arr, 0, arr.size() - 1);
	}

	public void sort(List<String> arr, int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;

			sort(arr, left, middle);
			sort(arr, middle + 1, right);

			merge(arr, left, middle, right);
		}
	}

	public void merge(List<String> arr, int left, int middle, int right) {
		int size1 = middle - left + 1;
		int size2 = right - middle;

		ArrayList<String> leftArr = new ArrayList<String>();
		ArrayList<String> rightArr = new ArrayList<String>();


		for (int i = 0; i < size1; i++) {
			leftArr.add(arr.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			rightArr.add(arr.get(middle + 1 + i));
		}

		int leftIndex = 0;
		int rightIndex = 0;
		int index = left;

		while(leftIndex < size1 && rightIndex < size2) {
			if (leftArr.get(leftIndex).compareTo(rightArr.get(rightIndex)) <= 0) {
				arr.set(index, leftArr.get(leftIndex));
				leftIndex++;
			}
			else {
				arr.set(index, rightArr.get(rightIndex));
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < size1) {
			arr.set(index, leftArr.get(leftIndex));
			leftIndex++;
			index++;
		}

		while (rightIndex < size2) {
			arr.set(index, rightArr.get(rightIndex));
			rightIndex++;
			index++;
		}
	}
}