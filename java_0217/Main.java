package java_0217;

import java_0131.meiriyiti.TreeNode;

import java.util.*;

public class Main {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root != null ){
            FindPathHelper(list,root,target);
        }
        return ans;
    }

    private void FindPathHelper(ArrayList<Integer> list, TreeNode root, int target) {
        if(root == null){
            return;
        }
        target -= root.val;
        list.add(root.val);

        if(target == 0 && root.left == null && root.right == null){
            ans.add(new ArrayList<>(list));
        }else{
            FindPathHelper(list,root.left,target);
            FindPathHelper(list,root.right,target);
        }
        list.remove(list.size()-1);
    }
    //思路一 ： 使用Map保存旧结点和新结点，构成映射关系
    //然后在遍历旧的链表，构造新链表的random.
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null){
            return null;
        }
        Map<RandomListNode,RandomListNode> map = new TreeMap<>(new Comparator<RandomListNode>() {
            @Override
            public int compare(RandomListNode o1, RandomListNode o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });
        RandomListNode fakeHead = new RandomListNode(-1);

        RandomListNode oldCur = pHead;
        RandomListNode newCur = fakeHead;

        while(oldCur != null){
            RandomListNode node = new RandomListNode(oldCur.val);

            newCur.next = node;
            map.put(oldCur,node);

            newCur = newCur.next;
            oldCur = oldCur.next;
        }
        oldCur = pHead;
        newCur = fakeHead.next;
        while(oldCur != null){
            if(oldCur.random != null){
                RandomListNode node = map.get(oldCur.random);
                newCur.random = node;
            }
            oldCur = oldCur.next;
            newCur = newCur.next;
        }
        return fakeHead.next;
    }
    public RandomListNode Copy(RandomListNode pHead){
        if(pHead == null){
            return null;
        }
        RandomListNode fakeNode = new RandomListNode(-1);

        RandomListNode cur = pHead;
        RandomListNode next = null;
        while(cur != null){
            next = cur.next;
            RandomListNode node = new RandomListNode(cur.val);

            cur.next = node;
            node.next = next;

            cur = next;
        }
        //处理Random
        cur = pHead;
        while(cur != null){
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }
        cur = pHead;
//        RandomListNode newCur = fakeNode;

//        while(cur != null){
//            next = cur.next.next;
//            newCur.next = cur.next;
//
//            newCur = newCur.next;
//            cur = next;
//        }
        RandomListNode newHead = pHead.next;
        while(cur != null){
            RandomListNode newCur = cur.next;

            cur.next = newCur.next;

            if(newCur.next != null){
                newCur.next = newCur.next.next;
            }
            cur = cur.next;
        }
        return newHead;
    }
}
