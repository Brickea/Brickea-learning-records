package unionfind;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description: 使用快速合并的方式加速并查集合并，此处思路为使用树的结构
 * 集合中每个节点链接其父节点
 * 根节点为当前节点 index 和其 value 相等的节点
 **/

public class QuickUnionUnionFind {
    private int[] id;

    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [N]
     * @description: 初始化树形结构存储，其根节点为自身，即所有节点都未和任意其他节点连接
     **/
    public QuickUnionUnionFind(int N) {
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [p]
     * @return: int
     * @description: 用来返回 p 节点的根节点
     **/
    public int root(int p) {
        while (this.id[p] != p) {
            p = this.id[p];
        }
        return p;
    }

    /**
    * @author: Zixiao Wang
    * @date: 8/3/2020
    * @param: [p, q]
    * @return: boolean
    * @description:
     * 判断节点 p 和 q 是否连接
     * 即寻找两个所在集合根节点是否相同
    **/
    public boolean find(int p, int q) {
        return this.root(p) == this.root(q);
    }
    
    /**
    * @author: Zixiao Wang
    * @date: 8/3/2020
    * @param: [p, q]
    * @return: void
    * @description:
     * 将节点 p 和节点 q 两者所在集合相连接
     * 此处即将 q 所在集合的根节点 连接到 p 所在集合的根节点
    **/
    public void union(int p,int q){
        int rootP = this.root(p);
        int rootQ = this.root(q);

        this.id[rootQ] = rootP;
    }
}
