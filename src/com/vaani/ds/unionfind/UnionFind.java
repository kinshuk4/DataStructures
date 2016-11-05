package com.vaani.ds.unionfind;

public class UnionFind {
  
  private int[] id;     // the component id of each element
  private int[] sizes;   // the size of each component
  private int count;    //  the number of disconnected component
  
  public UnionFind(int size) {
    id = new int[size];
    sizes = new int[size];
    for (int i = 0; i < size; ++i) {
      id[i] = i;
      sizes[i] = 1;
    }
    count = size;
  }
  
  /**
   * Union the two components that contains p and q.
   * @param p
   * @param q
   */
  public void union(int p, int q) {
    int pId = find(p);
    int qId = find(q);
    
    if (pId == qId) {   // already connected
      return;
    }
    // make smaller root point to larger one
    if (sizes[pId] <= sizes[qId]) {
      id[pId] = qId;
      sizes[qId] += sizes[pId];
    }
    else {
      id[qId] = pId;
      sizes[pId] += sizes[qId];
    }
    --count;
  }
  
  /**
   * Find the component id that p belongs to.
   * @param p
   * @return
   */
  public int find(int p) {
    int compId = p;
    while (compId != id[compId]) {
      id[compId] = id[id[compId]];
      compId = id[compId];
    }
    return compId;
  }
  
  /**
   * Identify whether p and q are connected.
   * @param p
   * @param q
   * @return
   */
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int count() {
    return count;
  }
}