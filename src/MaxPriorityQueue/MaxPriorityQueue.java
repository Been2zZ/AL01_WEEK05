package MaxPriorityQueue;

public class MaxPriorityQueue {

    int heapsize;
    Node[] MAX_HEAP;

    /** Max Heap */
    private void maxHeapify(Node[] A, int i, int size) {
        /** 특정 노드를 기점으로, 트리를 내려가며 최대 힙이 되도록 함 */

        // 루트에 최댓값 저장
        // 부모의 키 값 >= 자식의 키 값

        int L = left_child(i);         // 왼쪽 자식의 index
        int R = right_child(i);        // 오른쪽 자식의 index

        int largest, heapsize = size;
        Node temp;

        if(L <= heapsize && A[L].getKey() > A[i].getKey())
            largest = L;                // 매개변수 A의 i번째 index의 key값보다 왼쪽 자식 노드가 큰 경우
                                        // 변수 largest에 왼쪽 자식 index 저장
        else
            largest = i;                // 크지 않은 경우 (즉, A의 i번째 index의 key값이 더 큰 경우)
                                        // 변수 largest에 index i 저장

        if(R <= heapsize && A[R].getKey() > A[largest].getKey())
            largest = R;                // 위에서 비교 후, 큰 값으로 나온 largest index보다 index i의 오른쪽 자식이 큰 경우
                                        // 변수 largest에 오른쪽 자식 index 저장

        if(largest != i) {              // 위의 모든 비교문을 거쳐 제일 큰 값이 저장된 index인 largest가 i와 같지 않은 경우,
            temp = A[i];                // A의 i번째 인덱스와 A의 largest index swap
            A[i] = A[largest];
            A[largest] = temp;

            maxHeapify(A, largest, heapsize);       // swap 과정 후, largest 기점으로 heapify과정 진행
        }
    }

    private void buildMaxHeap(Node[] A, int size) {
        /** 입력받은 배열 A를 최대 Heap으로 만드는 함수 */

        // 자식을 갖는 마지막 노드부터 루트노드까지 순서대로 검사하며 maxHeapify함수 실행

        heapsize = size;                        // 매개변수로 입력받은 size를 heap의 크기로 저장

        for(int i = heapsize / 2; i >= 0; i--)  // 자식이 존재하는 노드부터 루트노드까지 maxHeapify함수 실행하며 Max heap 구축
            maxHeapify(A, i, heapsize);
    }

    private int parent(int i) {
        /** 부모 노드 index return */
        return i / 2;
    }

    private int left_child(int i) {
        /** 왼쪽 자식 노드 index return */
        return 2 * i;
    }

    private int right_child(int i) {
        /** 오른쪽 자식 노드 index return */
        return 2 * i + 1;
    }

    public void MaxHeap(Node[] A, int size) {
        /** 정렬을 위한 method */
        int heapsize = size - 1, k = 0;
        Node temp;
        MAX_HEAP = new Node[A.length];

        buildMaxHeap(A, heapsize);                  // 입력받은 배열 A에 대해 Max heap 구축

        for(int i = heapsize ; i >= 0; i--) {       // MAX_HEAP 배열에 내림차순으로 정렬된 값 저장
            temp = A[0];
            A[0] = A[i];
            A[i] = temp;

            MAX_HEAP[k++] = A[i];

            heapsize--;

            maxHeapify(A, 0, heapsize);          // 0번째 index 기점으로 heapify과정 진행
        }

        for(int i = 0; i < MAX_HEAP.length; i++)    // 입력 받은 배열 A에 정렬된 값 저장
            A[i] = MAX_HEAP[i];
    }

    /** Max Priority Queue */
    public Node[] insert(Node[] A, Node x, int size) {
        /** A에 원소 x를 새로 넣는다. */
        int heapsize = size;

        Node[] tempArr = new Node[A.length + 1];         // 입력받은 배열 A의 크기를 1 늘리기 위해 필요한 임시 배열 선언

        for(int i = 0; i < A.length; i++)
            tempArr[i] = A[i];
        A = tempArr;                                     // 배열 A 크기 1증가

        A[heapsize] = x;                                 // heap의 마지막 원소에 입력받은 원소 x 저장

        MaxHeap(A, heapsize + 1);                   // MaxHeap 함수를 통해 배열 A Max heap 유지

        return A;
    }

    public Node max(Node[] A) {
        /** A에서 키 값이 최대인 원소를 리턴한다. */
        return A[0];                          // Max heap으로 정렬된 배열에서 최대값은 루트 노드이므로 루트 노드 return
    }

    public Node extract_max(Node[] A) {
        /** A에서 키 값이 최대인 원소를 삭제한다. */

        /** sol 1
        Node max = max(A);                      // key값이 최대인 원소 저장
        A[0] = A[heapsize];                     // 루트 노드에 heap의 제일 마지막 원소 저장
        MaxHeap(A, heapsize--);                 // heap의 크기를 1 줄인 후, Max heap 유지
        return max;                             // 제거한 노드 return
         */

        /** sol 2 */
        return delete(A, max(A));               // max와 delete 함수를 호출해 key값이 최대인 원소를 삭제한다.
    }

    public void increase_key(Node[] A, Node x, int k) {
        /** 원소 x의 키 값을 k로 증가시킨다. 이때 k는 x의 현재 키 값보다 작아지지 않는다고 가정한다. */

        int index = findIndex(A, x);                 // 입력받은 노드 x의 index를 찾는다.

        if(A[index].getKey() < k) {                  // 변경하고자 하는 key값이 기존 key값보다 큰 경우
            A[index].setKey(k);                      // 노드의 key값을 변경하고,
            MaxHeap(A, heapsize + 1);           // Max heap 유지
        } else
            System.out.println("warning : 기존 key값보다 큰 값으로만 변경가능 합니다.");

    }

    public Node delete(Node[] A, Node x) {
        /** A에서 노드 x를 제거한다. 제거 후, Max Heap 유지 */

        int index = findIndex(A, x);                // 입력받은 제거하고자 하는 원소의 index를 찾는다.
        Node temp = A[index];                       // 제거하고자 하는 노드 저장

        A[index] = A[heapsize];                     // 제거하고자 하는 노드에 heap의 제일 마지막 원소 저장

        MaxHeap(A, heapsize--);                     // heap의 크기를 1 줄인 후, Max heap 유지

        return temp;                                // 제거한 노드 return
    }

    private int findIndex(Node[] A, Node x) {
        /** 원소 x의 index return */
        int index = -1;                                 // 변수 초기화

        for(int i = 0; i < heapsize + 1; i++) {         // 저장된 원소 갯수 만큼 반복
            if(A[i].getKey() == x.getKey())             // 입력 노드의 key값과 같은 key를 찾은 경우,
                index = i;                              // 변수에 값 저장
        }
        return index;                                   // index return
    }

    public Node findNode(Node[] A, int key) {
        /** key값을 통해 Node원소를 return */
        Node temp = null;

        for(int i = 0; i < heapsize + 1; i++) {         // 저장된 원소 갯수 만큼 반복
            if(A[i].getKey() == key)                    // 입력된 key값과 같은 key를 찾은 경우,
                temp = A[i];                            // 변수에 값 저장
        }
        return temp;                                    // Node return
    }

    public int exception(Node[] A, int key) {
        /** 예외처리를 위한 method */

        int temp = 0;

        for(int i = 0; i < heapsize + 1; i++) {         // 저장된 원소 갯수 만큼 반복
            if(A[i].getKey() != key)                    // 입력된 key값과 같은 key이 존재하지 않는 경우,
                temp = -1;
            else temp = 1;
        }
        return temp;
    }
}
