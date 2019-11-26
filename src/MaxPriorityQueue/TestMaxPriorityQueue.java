package MaxPriorityQueue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMaxPriorityQueue {
    public static Node[] insert(File F) throws FileNotFoundException {
        /** File read & Array(Node) insert */
        FileReader fr = new FileReader(F);
        BufferedReader br = new BufferedReader(fr);

        List<Node> list = new ArrayList<>();

        try {
            String line = "";
            while((line = br.readLine()) != null){
                String[] temp = line.split(", ");
                list.add(new Node(Integer.parseInt(temp[0]), temp[1]));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println(e);
        }

        Node[] Arr = new Node[list.size()];

        for(int i = 0; i < Arr.length; i++)
            Arr[i] = list.get(i);

        return Arr;
    }


    public static void main(String[] args) throws FileNotFoundException {
        MaxPriorityQueue q = new MaxPriorityQueue();

        File F = new File("data05.txt");
        Node[] A = insert(F);

        System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다. ****");
        for(int i = 0; i < A.length; i++)
            System.out.println(A[i].getKey() + ", " + A[i].getSubject());

        while(true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("1. 작업 추가        2. 최대값        3. 최대 우선순위 작업 처리");
            System.out.println("4. 원소 키값 증가    5. 최대값 제거    6. 작업 제거     7. 종료");
            System.out.println("-----------------------------------------------------");

            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            int key;
            String name;

            switch(input) {
                case 1:
                    /** 작업 추가 */
                    System.out.println("추가하고자 하는 과목의 key값을 입력하세요. : ");
                    key = sc.nextInt();
                    if(q.exception(A, key) == 1) {
                        System.out.println("중복되는 key값 입니다.");
                        break;
                    }
                    System.out.println("추가하고자 하는 과목의 이름값을 입력하세요. : ");
                    name = sc.next();
                    A = q.insert(A, new Node(key, name),  q.heapsize + 1);
                    for(int i = 0; i < q.heapsize + 1; i++)
                        System.out.println(A[i].getKey() + ", " + A[i].getSubject());
                    break;
                case 2:
                    /** 최대값 */
                    System.out.println(q.max(A).getKey() + ", " + q.max(A).getSubject());
                    break;
                case 3:
                    /** 최대 우선순위 작업 처리 */
                    q.MaxHeap(A, A.length);
                    for(int i = 0; i < q.heapsize + 1; i++)
                        System.out.println(A[i].getKey() + ", " + A[i].getSubject());
                    break;
                case 4:
                    /** 원소 키 값 증가 */
                    System.out.println("원소의 key값을 입력하세요. : ");
                    key = sc.nextInt();
                    if(q.exception(A, key) == -1) {
                        System.out.println("존재하지 않는 key값 입니다.");
                        break;
                    }
                    System.out.println("증가시키고자하는 key값을 입력하세요. : ");
                    int newkey = sc.nextInt();
                    q.increase_key(A, q.findNode(A, key), newkey);
                    for(int i = 0; i < q.heapsize + 1; i++)
                        System.out.println(A[i].getKey() + ", " + A[i].getSubject());
                    break;
                case 5:
                    /** 최대값 제거 */
                    q.extract_max(A);
                    for(int i = 0; i < q.heapsize + 1; i++)
                        System.out.println(A[i].getKey() + ", " + A[i].getSubject());
                    break;
                case 6:
                    /** 작업 제거 */
                    System.out.println("제거하고자 하는 과목의 key값을 입력하세요. : ");
                    key = sc.nextInt();
                    if(q.exception(A, key) == -1) {
                        System.out.println("존재하지 않는 key값 입니다.");
                        break;
                    }
                    q.delete(A, q.findNode(A, key));
                    for(int i = 0; i < q.heapsize + 1; i++)
                        System.out.println(A[i].getKey() + ", " + A[i].getSubject());
                    break;
                case 7:
                    /** 종료 */
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");

            }
        }
    }
}
