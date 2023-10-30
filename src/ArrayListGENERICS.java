import java.io.*;
import java.util.*;
import java.util.Random;

public class ArrayListGENERICS<T extends Comparable<T>> {

    public void swap(List<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public void bubbleSort(List<T> array) {
        bubbleSort(array, 0, array.size());
    }

    public void bubbleSort(List<T> array, int start, int end) {
        if (end - start <= 1)
            return;

        for (int i = start; i < end - 1; i++) {
            if (array.get(i).compareTo(array.get(i + 1)) > 0) {
                swap(array, i, i + 1);
            }
        }

        bubbleSort(array, start, end - 1);
    }

    public boolean isSorted(List<T> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            if (array.get(i).compareTo(array.get(i + 1)) > 0)
                return false;
        }
        return true;
    }

    public List<T> createRandomArray(int length) {
        List<T> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            
            array.add((T) Integer.valueOf(random.nextInt(100))); 
        }
        return array;
    }

    public void writeArrayToFile(List<T> array, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        for (T item : array) {
            fileWriter.write(item + "\n");
        }

        fileWriter.close();
    }

    public List<T> readArrayFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        List<T> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            arrayList.add((T) Integer.valueOf(Integer.parseInt(scanner.nextLine())));
        }
        scanner.close();
        return arrayList;
    }

    public void mergeSort(List<T> array) {
        mergeSort(array, 0, array.size());
    }

    public void mergeSort(List<T> array, int start, int end) {
        if (end - start <= 1)
            return;
        int middle = (start + end) / 2;
        mergeSort(array, start, middle);
        mergeSort(array, middle, end);
        merge(array, start, middle, end);
    }

    public void merge(List<T> array, int start, int middle, int end) {
        int i = start, j = middle, k = 0;
        List<T> mergedArray = new ArrayList<>(end - start);
        while (i < middle && j < end) {
            if (array.get(i).compareTo(array.get(j)) <= 0) {
                mergedArray.add(array.get(i));
                k++;
                i++;
            } else {
                mergedArray.add(array.get(j));
                k++;
                j++;
            }
        }
        while (i < middle) {
            mergedArray.add(array.get(i));
            k++;
            i++;
        }
        while (j < end) {
            mergedArray.add(array.get(j));
            k++;
            j++;
        }
        for (i = start; i < end; i++) {
            array.set(i, mergedArray.get(i - start));
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayListGENERICS<Integer> arrayListMOD = new ArrayListGENERICS<>();
        List<Integer> array = arrayListMOD.createRandomArray(10000000);
        System.out.println(arrayListMOD.isSorted(array));
        double start = (double) System.currentTimeMillis() / 1000;

        arrayListMOD.mergeSort(array);

        double timeSpent = (double) System.currentTimeMillis() / 1000 - start;

        System.out.println(arrayListMOD.isSorted(array));
        System.out.println("Time spent: " + timeSpent + " s");
    }
}
