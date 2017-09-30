
// filling hashmap with chars from a-z/A-Z    
int count =1;
        HashMap<Character,Integer> chars = new HashMap<>();
        for (char i='a';i<='z';++i){
            chars.put(i,count);
            count++;
}


 // Linear search
 // success - returns index
 // fail - returns -1
 // time complexity - O(n)
 public static int linear_search(int[] a,int key){
    int len = a.length;
    for(int i=0;i<len;i++){
        if(a[i]==key){
            return i;
        }
    }
    return -1;
 }



// Trie
// Complexity: O(N) where n=length of string
// for both insert n search
class Trie{

    TrieNode root = new TrieNode();

    class TrieNode{
        HashMap<Character,TrieNode> children;
        boolean isEnd;

        TrieNode(){
            children = new HashMap<>();
            isEnd = false;
        }
    }

    public void insert(String str){
        TrieNode current = root;
        int len = str.length();
        for (int i=0;i<len;i++){
            Character c = str.charAt(i);
            TrieNode node = current.children.get(c);
            if (node==null){
                node = new TrieNode();
                current.children.put(c,node);
            }
            current=node;
        }
        current.isEnd = true;
    }

    public boolean search(String str){
        TrieNode current = root;
        int len = str.length();
        for (int i=0;i<len;i++){
            Character c = str.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null){
                return false;
            }else{
                current = node;
            }
        }
        return true;
    }

}


// Disjoint Set with unionbyRank n pathCompression
// makeSet(int data) => makes an empty set and sets rank,parent to itself
// unionSet(int a,int b)
// findSet(int a)
//Complexity => o(m)
//where m=no of operations

class DisjointSet{
    
    class Node{

        int rank;
        int data;
        Node parent;

    }
    
    HashMap<Integer,Node> set = new HashMap<>();

    public void makeSet(int data){
        Node n = new Node();
        n.data = data;
        n.rank=0;
        n.parent=n;
        set.put(data,n);
    }

    public boolean unionSet(int a,int b){

        Node node1 = set.get(a);
        Node node2 = set.get(b);

        Node parent1 = find(node1);
        Node parent2 = find(node2);

        // for case (1,1)
        if (parent1.data == parent2.data){
            return false;
        }

        // if parent1 == parent2 => parent1++ n make it as parent
        if (parent1.rank == parent2.rank){
            parent1.rank++;
            parent2.parent = parent1;
        }

        if (parent1.rank > parent2.rank){
            parent2.parent = parent1;
        }else{
            parent1.parent = parent2;
        }

        return  true;
    }

    Node findSet(int data){
        return find(set.get(data));
    }

    Node find(Node node){
        Node parent = node.parent;
        if (parent == node){
            return parent;
        }
        node.parent = find(parent);
        return node.parent;
    }
}

// kMP substring search
// O(m+n) where m=pattern,n= length of text

  public static boolean kmpSearch(String text,String pattern) {

        int[] lps = lpsArray(pattern);
        int tlen = text.length();
        int plen = pattern.length();
        int i = 0;
        int j = 0;

        while (i < tlen && j < plen) {
            Character og = text.charAt(i);
            Character c = pattern.charAt(j);

            if (og.equals(c)) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j == plen) {
            return true;
        }
        return false;
 }

// helper method for kmp
// calculates longest suffix which is also a prefix
  public static int[] lpsArray(String pattern){

        int plen = pattern.length();
        int[] arr = new int[plen];
        arr[0]=0;
        int j=0;
        for (int i=1;i<plen;i++){
            Character at_i = pattern.charAt(i);
            Character at_j = pattern.charAt(j);
            if (at_i.equals(at_j)){
                arr[i]=j+1;
                i++;
                j++;
            }else{
                if (j!=0){
                     j=arr[j-1];
                }else{
                    arr[i]=0;
                    i++;
                }
            }
        }

        return arr;
    }

  

// Naive substring search
// O(mn)
 public  static void naiveSearch(String text,String pattern){

        int tlen = text.length();
        int plen = pattern.length();
        boolean isfound = false;

        for (int i=0;i<=tlen-plen;i++){

            int j;

            for (j=0;j<plen-1;j++){

                Character t = text.charAt(i+j);
                Character og = pattern.charAt(j);
                if (!(t.equals(og))){
                    break;
                }
            }
            if (j == plen-1) {
                isfound=true;
                System.out.println(i);
            }
        }
        if (!isfound){
            System.out.println(-1);
        }
 }

// Rabin Karp String Algo
// Complexity: O(m+n) worse => O(mn)
// i/p => pattern,text,primeNo
// o/p =>  prints -> index of occurance
 static void rabinKarp(String pat, String txt,int q)
    {
        int d = 256;
        boolean isFound = false;

        int plen = pat.length();
        int tlen = txt.length();
        int phash = 0; // hash value for pattern
        int thash = 0; // hash value for txt
        int hash = 1;

        for (int i = 0; i < plen-1; i++)
            hash = (hash*d)%q;


        for (int i = 0; i < plen; i++)
        {
            phash = (d*phash + pat.charAt(i))%q;
            thash = (d*thash + txt.charAt(i))%q;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= tlen - plen; i++)
        {

            if ( phash == thash )
            {
                int j;
                /* Check for characters one by one */
                for ( j = 0; j < plen; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }

                if (j == plen) {
                    isFound = true;
                    System.out.println(i);
                }
            }


            if ( i < tlen-plen )
            {
            	// remove leading char , add trailing one , 
                thash = (d*(thash - txt.charAt(i)*hash) + txt.charAt(i+plen))%q;

                if (thash < 0)
                    thash = (thash + q);
            }
        }
        if (!isFound){
            System.out.println(-1);
        }
 }


 // binary search
 // success - returns index
 // fail - returns -1
 // time complexity - O(logn)
 public static int binary_search(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi )/ 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
}


// Strassen Matrix Multiplication 
// Complexity : O(n2)
// naive - multiply takes - O(n3)
// i/p : 2 matrices
// o/p : resultant matrix

private static int[][] StrassenMultiply(int[][] A, int[][] B) {

        int n = A.length;

        int[][] res = new int[n][n];

        // if the input matrix is 1x1
        if (n == 1) {
            res[0][0] = A[0][0] * B[0][0];
        } else {

            // first matrix
            int[][] a = new int[n / 2][n / 2];
            int[][] b = new int[n / 2][n / 2];
            int[][] c = new int[n / 2][n / 2];
            int[][] d = new int[n / 2][n / 2];
            
            // second matrix
            int[][] e = new int[n / 2][n / 2];
            int[][] f = new int[n / 2][n / 2];
            int[][] g = new int[n / 2][n / 2];
            int[][] h = new int[n / 2][n / 2];

            // dividing matrix A into 4 parts
            divideArray(A, a, 0, 0);
            divideArray(A, b, 0, n / 2);
            divideArray(A, c, n / 2, 0);
            divideArray(A, d, n / 2, n / 2);

            // dividing matrix B into 4 parts
            divideArray(B, e, 0, 0);
            divideArray(B, f, 0, n / 2);
            divideArray(B, g, n / 2, 0);
            divideArray(B, h, n / 2, n / 2);
            
             /** 
              p1 = (a + d)(e + h)
              p2 = (c + d)e
              p3 = a(f - h)
              p4 = d(g - e)
              p5 = (a + b)h
              p6 = (c - a) (e + f)
              p7 = (b - d) (g + h)
            **/
           
            
            
            int[][] p1 = StrassenMultiply(addMatrices(a, d), addMatrices(e, h));
            int[][] p2 = StrassenMultiply(addMatrices(c,d),e);
            int[][] p3 = StrassenMultiply(a, subMatrices(f, h));           
            int[][] p4 = StrassenMultiply(d, subMatrices(g, e));
            int[][] p5 = StrassenMultiply(addMatrices(a,b), h);
            int[][] p6 = StrassenMultiply(subMatrices(c, a), addMatrices(e, f));
            int[][] p7 = StrassenMultiply(subMatrices(b, d), addMatrices(g, h));

            
           /**
              C11 = p1 + p4 - p5 + p7
              C12 = p3 + p5
              C21 = p2 + p4
              C22 = p1 - p2 + p3 + p6
            **/
           
            int[][] C11 = addMatrices(subMatrices(addMatrices(p1, p4), p5), p7);
            int[][] C12 = addMatrices(p3, p5);
            int[][] C21 = addMatrices(p2, p4);
            int[][] C22 = addMatrices(subMatrices(addMatrices(p1, p3), p2), p6);

            // adding all subarray back into one
            copySubArray(C11, res, 0, 0);
            copySubArray(C12, res, 0, n / 2);
            copySubArray(C21, res, n / 2, 0);
            copySubArray(C22, res, n / 2, n / 2);
        }
        return res;
    }

    // helper methods    
    // Adding 2 matrices
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    // Subtracting 2 matrices
    public static int[][] subMatrices(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] - b[i][j];
            }
        }
        return res;
    }

    // print matrix
    public static void printMatrix(int[][] a) {
        int n = a.length;
        System.out.println("Resultant Matrix ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // divides array
    public static void divideArray(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    // copies
    public static void copySubArray(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    // max of 2 numbers
    public static int max(int x, int y) {
        return (x>y)? x:y;
    }

    // min of 2 numbers
    public static int min(int x, int y) {
        return (x<y)? x:y;
    }




class Graph{

    private int vertices;
    private LinkedList<Integer> adj[];
    private boolean[] visited = new boolean[vertices];

    Graph(int vertices){
        this.vertices = vertices;
        adj = new LinkedList[vertices];
        for (int i=0;i<vertices;++i){
            adj[i] = new LinkedList<>();
        }
    }

    void addEdge(int from,int to){
        adj[from].add(to);
    }

    // -------------- pattern searching ----------


    // abcd
    // cd

    void naiveSearch(String text,String pattern){

        int tlen = text.length();
        int plen = text.length();

        for (int i=0;i<=tlen-plen;i++){

            for (int j=0;j<plen;j++){
                if (!text.charAt(i+j).equals(pattern.charAt(j)))
                    break;
            }

            System.out.println(i);
        }
    }

    void

    // iterative DFS
    // complexity : O(V+E)
    void DFS(int vertex){

        Stack<Integer> stack = new Stack<>();

        stack.push(vertex);

        while (!stack.isEmpty()){

            vertex = stack.peek();

            stack.pop();

            // Stack may contain same vertex twice. So
            // we need to print the popped item only
            // if it is not visited.
            if(!visited[vertex])
            {
                System.out.print(vertex + " ");
                visited[vertex]=true;
            }

            Iterator<Integer> itr = adj[vertex].listIterator();

            while (itr.hasNext())
            {
                int v = itr.next();
                if(!visited[v])
                    stack.push(v);
            }
        }

    }


    // iterative BFS
    // complexity : O(V+E)
    void BFS(int vertex){

        LinkedList<Integer> queue = new LinkedList<>();

        visited[vertex]=true;

        queue.add(vertex);

        while (queue.size() !=0){

            vertex = queue.poll();

            // print vertex

            Iterator<Integer> itr = adj[vertex].listIterator();

            while (itr.hasNext()){
                int n = itr.next();
                if (!visited[n]){
                    visited[n]=true;
                    queue.add(n);
                }
            }
        }
    }


    // recursive DFS
    // complexity: O(V+E)
    void DFS(int vertex){

        visited[vertex] = true;

        // print vertex

        Iterator<Integer> i = adj[vertex].listIterator();
        while (i.hasNext()){
            int n = i.next();
            if(!visited[n]){
                DFSUtil(n,visited);
            }
        }
    }

    // recursive BFS
    // complexity : O(V+E)
    void BFS(int vertex){

        LinkedList<Integer> queue = new LinkedList<>();

        visited[vertex] = true;

        queue.add(vertex);

        while (queue.size()!=0){
            s = queue.poll();

            // print vertex

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()){
                int n = i.next();
                if (!visited[n]){
                    BFS(vertex);
                }
            }
        }

    }


    // finding connected components - DFS
    int connectedComponents(int vertices){
        int count = 0;
        for (int i=0;i<vertices;++i){
            if (visited[i] == false){
                count++;
                DFS(i);
            }
        }
        return count;
    }

    // finding connected components - BFS
    int connectedComponents(int vertices){
        int count = 0;
        for (int i=0;i<vertices;++i){
            if (visited[i] == false){
                count++;
                BFS(i);
            }
        }
        return count;
    }



}

Graph g = new Graph(4);
g.addEdge(0,1);
g.addEdge(1, 2);
g.addEdge(2, 0);
g.addEdge(2, 3);
g.addEdge(3, 3);
g.DFS(2);


// djikstra
// floyd warshall
// bellman ford

// Karatsuba Multiplication
// Naive approach takes - O(n2)
// Complexity - O(n 1.5)





     
