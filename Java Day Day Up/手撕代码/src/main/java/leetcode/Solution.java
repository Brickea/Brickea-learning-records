class Solution {
    private char[][] map;
    private boolean[][] visited;
    private void bfsHelper(int row, int col){
        // bfs 所有 1 的位置
        // 并将 visited 置为 true
        
        if(map[row][col]=='1'&&!visited[row][col]){
            visited[row][col]=true;
            if(row-1>-1){
                bfsHelper(row-1,col);
            }
            if(row+1<map.length){
                bfsHelper(row+1,col);
            }
            if(col-1>-1){
                bfsHelper(row,col-1);
            }
            if(col+1<map[0].length){
                bfsHelper(row,col+1);
            }
        }else{
            return ;
        }
        
    }
    
    public int numIslands(char[][] grid) {
        // 每当遇到 island 的数字，就从该数字开始进行 bfs
        // 并将遇到的位置 设置为 visited = true 减少重复遍历
        map = grid;
        int ROW = map.length;
        int COL = ROW==0?0:map[0].length;
        visited = new boolean[ROW][COL];
        
        
        int res = 0;
        
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                if(!visited[i][j]){
                    // 没有拜访过
                    if(map[i][j]=='1'){
                        // 如果是岛就开始 bfs
                        res++;
                        bfsHelper(i,j);
                    }
                }
            }
        }
        
        return res;
        
    }
}