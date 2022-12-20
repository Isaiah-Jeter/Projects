def edit_distance(first, second):
# x are columns, y are rows
    lf,ls=(len(first)+1), (len(second)+1)
    arr=[]
    for i in range(lf):
        arr.append([0]*ls)
    
    
    for i in range(len(arr[0])):
        arr[0][i]=i

    for i in range(len(arr)):
        arr[i][0]=i
        # print(arr)

    for x in range(1, lf):
        for y in range(1, ls):
            top=arr[x][y-1] + 1
            diag=arr[x-1][y-1] + 1
            left=arr[x-1][y] + 1
            arr[x][y]=min(top,diag,left)
            if first[x-1]==second[y-1]:
                arr[x][y]=arr[x-1][y-1]
    # print(arr) 
    return arr[lf-1][ls-1]
