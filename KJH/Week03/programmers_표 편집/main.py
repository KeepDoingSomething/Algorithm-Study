class Node:
    def __init__(self, data = None):
        self.data = data
        self.prev = None
        self.next = None


class DList:
    def __init__(self, n, k):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

        # 삭제한 노드들을 저장할 스택
        self.stack = []

        self.size = n
        for i in range(n):
            node = Node(i)            
            # 노드가 추가되기 전, 마지막 노드
            lastNode = self.tail.prev
            node.prev = lastNode
            node.next = self.tail
            lastNode.next = node
            self.tail.prev = node

        # 현재 노드 curr
        self.curr = self.head.next
        for _ in range(k):
            self.curr = self.curr.next        

    '''
    노드 삭제
    '''
    def delete(self):
        # 삭제할 노드를 스택에 추가
        self.stack.append(self.curr)
        delNode = self.curr
        # 링크드 리스트에서 노드 삭제
        delNode.prev.next = delNode.next
        delNode.next.prev = delNode.prev

        # 현재 노드 위치 조정.
        self.curr = self.curr.next
        if self.curr == self.tail:
            self.curr = self.tail.prev   

    '''
    노드 되돌리기
    '''
    def undo(self):
        node = self.stack.pop()
        node.prev.next = node
        node.next.prev = node

    '''
    step만큼 이전 노드로
    '''
    def up(self, step):
        for _ in range(step):
            self.curr = self.curr.prev

    '''
    step만큼 다음 노드로
    '''
    def down(self, step):
        for _ in range(step):
            self.curr = self.curr.next

    '''
    OX 배열
    '''
    def ox(self):
        ret = ["O"] * self.size

        for node in self.stack:
            i = node.data
            ret[i] = "X"

        return ret
       
        

def solution(n, k, cmds):
    myList = DList(n, k)

    for cmd in cmds:
        if cmd == "C":
            myList.delete()
        elif cmd == "Z":
            myList.undo()
        else:
            direction, step = cmd.split()
            if direction == "U":
                myList.up(int(step))
            else:
                myList.down(int(step))
    
    # OX배열을 문자열로
    return ''.join(myList.ox())