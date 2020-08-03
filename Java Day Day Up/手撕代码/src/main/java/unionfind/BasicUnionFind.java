package unionfind;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 基础并查集，提供“连接两个节点”、“合并两个连接集”的功能
 **/

public class BasicUnionFind {
    private int[] id;

    /**
    * @author: Zixiao Wang
    * @date: 8/3/2020
    * @param: [N]
    * @return:
    * @description:
     * 用来初始化并查集，会依据 N 的大小初始化对应的存储数组
     * 代表一共有 N 个点，在最初都和自己链接
    **/
    public BasicUnionFind(int N){
        this.id = new int[N];
        for(int i=0;i<N;i++){
            this.id[i] = i;
        }
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/3/2020
    * @param: [p, q]
    * @return: boolean
    * @description:
     * 检查 p 节点和 q 节点是否连接
     * 返回判断，true 为链接， false 为没有链接
    **/
    public boolean find(int p,int q){
        return this.id[p]==this.id[q];
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/3/2020
    * @param: [p, q]
    * @return: void
    * @description:
     * 用来将两个节点连接，需要判断两个节点是否自己属于某一集合
     * 如果有则将两个集合链接
    **/
    public void union(int p,int q){
        int pid = this.id[p];
        int qid = this.id[q];

        for(int i=0;i<this.id.length;i++){
            if(qid==this.id[i]){
                // 将所有处于 q 节点集合的所有节点链接到 p 节点所处集合
                this.id[i]=pid;
            }
        }
    }
}
