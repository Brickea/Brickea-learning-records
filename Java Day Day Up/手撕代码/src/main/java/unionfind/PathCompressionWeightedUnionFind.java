package unionfind;

/**
 * @Project: IntelliJ IDEA
 * @Author: Zixiao Wang
 * @Description:
 **/

public class PathCompressionWeightedUnionFind {
    private int[] id;
    private int[] sz;

    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [N]
     * @description: 初始化树形存储结构
     **/
    public PathCompressionWeightedUnionFind(int N) {
        this.id = new int[N];
        this.sz = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
            this.sz[i] = 1;
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
            id[p] = id[id[p]]; // Just add this one line!!! And the performance improve a lot!
            p = this.id[p];
        }
        return p;
    }

    /**
     * @author: Zixiao Wang
     * @date: 8/3/2020
     * @param: [p, q]
     * @return: boolean
     * @description: 判断节点 p 和 q 是否连接
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
     * @description: 将节点 p 和节点 q 两者所在集合相连接
     * 此处即将 q 所在集合的根节点 连接到 p 所在集合的根节点
     **/
    public void union(int p, int q) {
        int rootP = this.root(p);
        int rootQ = this.root(q);

        if (this.sz[rootQ] > this.sz[rootP]) {
            // Q 所在树形结构的大小大于 P 所在的树形结构
            // 将 P 连接到 Q 的根节点上
            this.id[rootP] = rootQ;
            this.sz[rootQ] += this.sz[rootP];
        }else{
            // 反之亦然
            this.id[rootQ] = rootP;
            this.sz[rootP] += this.sz[rootQ];
        }
    }
}
