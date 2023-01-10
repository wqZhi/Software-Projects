import heapq

def manhattan_distance(node, goal):
    x_dis = abs(int(node['x']) - int(goal['x']))
    y_dis = abs(int(node['y']) - int(goal['y']))
    return x_dis + y_dis

def cal_cord(array2D):
    storeList = []
    for i in range(len(array2D)):
        for j in range(len(array2D)):
            temp_dict = {'val': array2D[i][j], 'x': j + 1, 'y': i + 1}
            storeList.append(temp_dict)
    return storeList

def cal_h(temp_list):
    h = 0
    for j in range(len(final_list)):
        val_to_cal = temp_list[j]
        if (val_to_cal == 0):
            continue
        val_in_correct_cord = next(item for item in final_state_cord if item["val"] == val_to_cal)
        x = [temp_list[3 * i: 3 * (i + 1)] for i in range(3)]
        swapCord = cal_cord(x)
        sum = manhattan_distance(swapCord[j], val_in_correct_cord)
        h += sum
    return h


global final_state_cord, final_list, final_state_array2D
final_list = [1, 2, 3, 4, 5, 6, 7, 8, 0]
final_state_array2D = [final_list[3 * i: 3 * (i + 1)] for i in range(3)]
final_state_cord = cal_cord(final_state_array2D)


def print_succ(state):
    initi_state_array2D = [state[3 * i: 3 * (i + 1)] for i in range(3)]
    initi_state_cord = cal_cord(initi_state_array2D)

    val_nearbyZero = []
    zero_info = next(item for item in initi_state_cord if item["val"] == 0)
    for i in range(len(initi_state_cord)):
        sum = manhattan_distance(initi_state_cord[i], zero_info)
        if (sum) == 1: val_nearbyZero.append(initi_state_cord[i])

    # swap method and print list and h
    result = []
    for i in range(len(val_nearbyZero)):
        temp_list = []
        temp_list.extend(state)
        val_pos = temp_list.index(val_nearbyZero[i]['val'])
        zero_pos = temp_list.index(0)
        temp_list[zero_pos], temp_list[val_pos] = temp_list[val_pos], temp_list[zero_pos]
        h = cal_h(temp_list)
        print(temp_list, 'h='+str(h))
        result.append(temp_list)

    return result

def checkExist(list, expand):
    for j in range(len(list)):
        if expand == list[j][1]:
            return True

    return False

def solve(state):
    open_pq = []
    close = []

    heapq.heappush(open_pq,(0 + cal_h(state), state, (0, cal_h(state), -1)))

    while (len(open_pq) > 0):
        #3
        father = heapq.heappop(open_pq)
        close.append(father)
        #4
        if father[1] == final_list:
            break

        expand = print_succ(father[1])
        for i in range(len(expand)):

            if (not checkExist(open_pq, expand[i])) or (not checkExist(close, expand[i])):
                h = cal_h(expand[i])
                g = father[2][0] + 1
                f = g+h
                parentIndex = close.index(father)
                expand[i] = (f, expand[i], (g,h, parentIndex))
                heapq.heappush(open_pq, expand[i])

            if checkExist(open_pq, expand[i]) or checkExist(close, expand[i]):
                for j in range(len(open_pq)):
                    if open_pq[j][1] == expand[i]:
                        if open_pq[j][2][0] < father[2][0] + 1:
                            temp = list(open_pq[j])
                            temp = (father[2][0] + 1 + cal_h(expand[i]), expand[i], (father[2][0] + 1, cal_h(expand[i]), close.index(father)))
                            open_pq[j] = tuple(temp)

    lastOutput = close[-1]

    #generate list that contain number of None val.
    list_val = []
    list_val = [None] * (lastOutput[2][0] + 1)
    parentPtr = lastOutput[2][2]

    #find the loop time
    step = lastOutput[2][0]

    #extract only the state, h, g
    valToCheck = (lastOutput[1], lastOutput[2][1], lastOutput[2][0])
    list_val[step] = valToCheck

    for i in range(step):
        lastOutput = close[parentPtr]
        parentPtr = lastOutput[2][2]
        s = lastOutput[2][0]
        valToCheck = (lastOutput[1], lastOutput[2][1], lastOutput[2][0])
        list_val[s] = valToCheck

    for c in list_val:
        print(c[0], 'h=' + str(c[1]), 'moves: ' + str(c[2]))

