import java.util.Arrays;

public class PQBH {
    private int capacity = 10;
    private int size = 0;
    private int[] items = new int[capacity];

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private int parent(int index) {
        return items[getParentIndex(index)];
    }

    private int leftChild(int index) {
        return items[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return items[getRightChildIndex(index)];
    }

    private void ensureCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty.");
        }
        return items[0];
    }

    public int poll() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty.");
        }

        int minItem = items[0];
        items[0] = items[size - 1];
        size--;

        heapifyDown();

        return minItem;
    }

    public void add(int item) {
        ensureCapacity();
        items[size] = item;
        size++;

        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && items[index] < parent(index)) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (items[index] < items[smallerChildIndex]) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    private void swap(int index1, int index2) {
        int temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        PQBH pq = new PQBH();

        pq.add(10);
        pq.add(5);
        pq.add(12);
        pq.add(3);
        pq.add(8);

        System.out.println("Extracted minimum: " + pq.poll()); // Output: 3
        System.out.println("Extracted minimum: " + pq.poll()); // Output: 5

        pq.add(4);
        pq.add(1);

        while (!pq.isEmpty()) {
            System.out.println("Extracted minimum: " + pq.poll());
        }
    }
}
