using System;
using System.Collections.Generic;
public class Solution
{
    public IList<int> RemainingMethods(int n, int k, int[][] invocations)
    {

        List<int>[] graph = new List<int>[n];
        for (int i = 0; i < n; i++)
            graph[i] = new List<int>();

        foreach (var edge in invocations)
        {
            graph[edge[0]].Add(edge[1]);
        }
        bool[] suspicious = new bool[n];
        DFS(k, graph, suspicious);
        foreach (var edge in invocations)
        {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to])
            {
                List<int> all = new List<int>();
                for (int i = 0; i < n; i++)
                    all.Add(i);
                return all;
            }
        }
        List<int> result = new List<int>();
        for (int i = 0; i < n; i++)
        {
            if (!suspicious[i])
                result.Add(i);
        }

        return result;
    }

    private void DFS(int node, List<int>[] graph, bool[] suspicious)
    {
        if (suspicious[node])
            return;

        suspicious[node] = true;

        foreach (int next in graph[node])
        {
            DFS(next, graph, suspicious);
        }
    }
}