### A. 基础掌握（闭卷 15–20 分钟）

1. **概念判断（对/错）**

   - a) 将 `IntNode` 设为 `static` 能减少每个节点对外部类实例的隐式引用。

     - 对，~~static声明意味着该静态类的方法无法访问静态类以外的任何成员~~,静态嵌套类**不再隐式携带对外部 SLList 实例的引用**，从而每个节点少占 8 B 左右内存；它依然可以访问外部类的 **静态** 成员。

   - b) 有了 `size` 缓存，`addFirst/Last` 的渐进复杂度一定不会变。

     - ~~错，不是一定不会变。若之前是遍历求size，而现在添加int size后，会O(n)变成O(1)~~
     - **对**
       ▪ `addFirst` 本来就是 O(1)，加缓存不变。
       ▪ `addLast` 在**不带尾指针**的 SLList 中仍需遍历 O(n)。`size` 缓存只把 **查询 size()** 从 O(n) 降到 O(1)。

   - c) 使用哨兵节点后，可以完全消除对空表的特判。

     - 对，有了哨兵节点(不为空), 对读操作如 `getFirst()` 仍需判空，否则会抛异常。仅在 **修改链表头/尾** 时能省掉分支。

      **目标**：能口头解释每一题“为什么”。（答案：a 对；b 不一定，对查询有利但更新多一步；c 在大多数头部/尾部操作可消特判，但个别 API（如 `getFirst` 空表）仍需考虑。）

2. **读代码找错（简答）**
    指出以下代码的两个 bug，并写出修正：

   ```java
   public void addLastIterative(int x){
       IntNode p = first;
       while (p != null) p = p.next;
       p.next = new IntNode(x, null);
   }
   ```

   **期望要点**：循环条件应为 `p.next != null`；空表时 `first == null` 要先创建首节点。

   ```java
   // 修改后
   public void addLastIterative(int x){
       if(first == null){
           first = new IntNode(x,null);
           size++;
           return;
       }
       IntNode p = first;
       while (p.next != null) p = p.next;
       p.next = new IntNode(x, null);
   }
   ```

   

3. **口述不变量（Invariants）**
    说出你实现中的 3 条不变量，并举例说明它们如何帮助你查错（如 `size` 一致性、`sentinel` 永不为 `null`、首元素位置固定在 `sentinel.next`）。

   - sentinel永不为null
     - ② 如果 `size==0` 则 `sentinel.next==null`，反之首结点在 `sentinel.next`；
     - ③ `size` 等于链表真实元素数。可在每个公有方法结尾用 `assert` 验证。

### B. 进阶巩固（编码 40–60 分钟）

1. **实现 `removeFirst()` 与 `getLast()`（哨兵版）**

   - `removeFirst()`：空表时抛异常；否则删除 `sentinel.next` 并 `size--`。
   - `getLast()`：遍历到 `p.next == null` 的节点返回其 `item`；空表抛异常。
      **要求**：所有路径保持不变量成立，`size` 正确维护。

   ```java
   public int removeFirst(){
       //int first = sentinel.next;
       IntNode node first = sentinel.next;
       if(first == null) return 0 ;
       sentinel.next = first.next;
       size--;
       
       return first.item;
   }
   
   public int getLast(){
       //int first = sentinel.next;
       IntNode node first = sentinel.next;
       if(first == null) return ;
       while(first.next != null) first = first.next;
       return first.item
   }
   ```

   

2. **补强 `size(IntNode p)`**（仅练习，不一定保留到最终类）

   - 写出 **递归** 与 **迭代** 两个版本，均需支持 `p == null`。比较二者在深链上的栈风险。

   ```java
   //迭代
   private static int size(IntNode p){
       int n = 0;
       IntNode cur = p; 
       while(cur != null){
           cur = cur.next;
           n++；
       }
       return n;
   }
   //递归
   private static int size(IntNode p){
       if(p == null) return 0;
       return 1 + size(p.next);
   }
   ```

   递归版有栈溢出风险；迭代版永远安全。

​	参考代码：

```java
public int removeFirst() {
    if (size == 0) {
        throw new NoSuchElementException("SLList is empty");
    }
    IntNode node = sentinel.next;
    sentinel.next = node.next;
    size--;
    return node.item;
}

public int getLast() {
    if (size == 0) {
        throw new NoSuchElementException("SLList is empty");
    }
    IntNode p = sentinel.next;
    while (p.next != null) {
        p = p.next;
    }
    return p.item;
}
```



1. **写 3 条断言**（或 JUnit 断言）

   - 在 `addFirst/addLast/removeFirst` 结束处断言：不变量 1–3 恒真。

   ```java
   public void addLast(int x) {
       IntNode cur = sentinel;
       while (cur.next != null) cur = cur.next;
       cur.next = new IntNode(x, null);
       size++;
   
       assert sentinel != null;
       assert (size == 0 && sentinel.next == null) ||
              (size > 0  && sentinel.next != null);
       assert size == rSize(sentinel.next); // 或 iSize
   }
   ```

   

### C. 挑战加分（思考 40–90 分钟）

1. **尾指针优化**

   - 为哨兵链表增加 `last` 指针，使 `addLast` 摊还 O(1)。注意：删除首/尾时如何维护 `last`？空表转非空、再回空的边界怎么处理？

   ```java
   private IntNode last; // 哨兵版新增
   
   public SLList() {
       sentinel = new IntNode(63, null);
       last = sentinel;  // 空表时 last = sentinel
   }
   public void addLast(int x) {
       last.next = new IntNode(x, null);
       last = last.next;          // 更新尾指针
       size++;
   }
   ```

2. **范型化**

   - 将 `SLList` 改为 `SLList<T>`，让链表可存任意类型。

   - 思考：与装箱 `int` 的开销相比，`int` 专用版本的意义？

     ```java
     public class SLList<T> {
         private static class IntNode<T> {
             T item;
             IntNode<T> next;
             ...
         }
         ...
     }
     // int 专版避免频繁装箱/拆箱产生的对象和 GC 压力，适合性能敏感场景。
     ```

     

3. **循环检测与防御**

   - 实现 Floyd 判圈（快慢指针）。
   - 在 `toString()` 中若检测到环，给出友好提示，防止无限循环。
   - 解释为何私有化能从源头降低造环风险。

   ```java
   public boolean hasCycle(){
       IntNode slow = sentinel, fast = sentinel;
       while (fast != null && fast.next != null) {
           slow = slow.next;
           fast = fast.next.next;
           if (slow == fast) return true;
       }
       return false;
   }
   
   ```

   

4. **对比题：带哨兵 vs 不带哨兵**

   - 写出 `addFirst/addLast/removeFirst` 两个版本的伪代码，比较“是否需要空表特判”的行数与复杂度。

   | 版本       | 伪代码                                                       | 是否特判空表 |
   | ---------- | ------------------------------------------------------------ | ------------ |
   | **无哨兵** | `if (first == null) first = newNode; else { newNode.next = first; first = newNode; }` | **要**       |
   | **哨兵**   | `newNode.next = sentinel.next; sentinel.next = newNode;`     | **不用**     |

------

## 四、参考改写（关键片段）

- **修正的构造与迭代尾插（非哨兵版）**

  ```java
  public SLList(int x) {
      first = new IntNode(x, null); // 注意：第二个参数用 null
      size = 1;
  }
  
  public void addLast(int x) {
      if (first == null) {
          first = new IntNode(x, null);
      } else {
          IntNode p = first;
          while (p.next != null) p = p.next; // 条件写 p.next != null
          p.next = new IntNode(x, null);
      }
      size += 1;
  }
  ```

  （问题来源与修正要点见：构造误用与循环条件。  ）

- **更健壮的 `size` 辅助**

  ```java
  private static int size(IntNode p) {
      if (p == null) return 0;           // 空表兜底
      if (p.next == null) return 1;
      return 1 + size(p.next);
  }
  ```

  （原版未处理 `p == null`。）

------

## 五、学习建议（如何验证“我真的掌握了”）

- **黑盒对拍**：随机序列（插入/删除/取首/取尾/取大小），同时操作 `java.util.LinkedList<Integer>` 与你的 `SLList`，比较每步结果是否一致。
- **边界覆盖**：空表、单元素、多元素、反复“变空→变非空”的循环。
- **不变量报警**：在关键操作末尾 `assert` 不变量，尝试“故意漏掉一次 `size++`”看看能否被捕获。
- **复杂度实验**：构造 10^5、10^6 规模数据，比较缓存前后的 `size()` 调用耗时数量级差异（无需追求精确数值，只看数量级）。

