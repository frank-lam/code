/**
 * 一个淘宝的订单中包含n(10>=n>=1)种商品A1，A2，...，An，每种商品数量分别为a1，a2，...，an个，记做{a1,a2,...,an}(ak>0)。
 * 订单在仓库生产过程中，仓库为了提升作业效率，会提前对热门组合商品进行预包装。
 * 假设这n个商品有m(9>=m>=1)个商品组合，每个组合bomk包含A1，A2，...，An的数量分别为{b1,b2,...,bn}(bk>=0,至少存在一个bk>0)
 * 举例如下：
 * 订单包含A，B，C商品，数量为{2，3，1}，商品组合bom1{2，1，1}，bom2{1，1，0}，bom3{0，1，1}
 * 对以上订单匹配给定商品组合，得到的可能匹配结果为：res1.匹配到组合1一套，剩余B商品；res2.匹配到组合2两套，组合3一套，不剩商品；
 * 现要求订单的最优匹配，最优匹配的原则为：1.匹配组合后，剩余商品种类数越少越好；2.在剩余商品种类数相同的情况下，匹配到的组合种类数越少越好；
 * 例如上面例子，我们认为res2优于res1。
 * 现需要编写程序，输入格式为：
 * n,m
 * a1,a2,...,an
 * bom1,b11,b12,...,b1n
 * bom2,b21,b22,...,b2n
 * ....
 * bomm,bm1,bm2,...,bmn
 * 输入数据的格式说明（数据间使用英文逗号分隔）：
 * 第一行数据：n个商品，m个预包方案
 * 第二行数据：商品1个数，商品2个数，。。。，商品n个数
 * 第三行数据：bom1，商品1个数，商品2个数，。。。，商品n个数
 * 第n-1行数据：。。。。
 * 第n行数据：bomn，商品1个数，商品2个数，。。。，商品n个数
 * 
 * 针对输入数据找出最优匹配，输出最优匹配的组合及套数，比如针对上面的例子输出：
 * match result:
 * bom2*2，bom3*1(bom2 2套，bom3 1套)
 * 注：输出结果有多个时可以乱序
 */
package com.lk.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	// 记录所有产品剩余数量的变量数组finalSum和记录每种组合方案的数量的数组r
	static private List<Integer> r = null, finalSum = null;

	/** 请完成下面这个函数，实现题目要求的功能 **/
	/** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^ **/
	public static void main(String[] args) {

		List<Integer> order = new ArrayList<Integer>();  // 每个商品的数量
		Map<String, List<Integer>> boms = new HashMap<String, List<Integer>>(); // 组合的种类

		Scanner in = new Scanner(System.in);
		String line = in.nextLine();

		Integer n = Integer.parseInt(line.split(",")[0]); // 商品种类
		Integer m = Integer.parseInt(line.split(",")[1]); // 商品组合的种类数

		line = in.nextLine();
		String[] itemCnt = line.split(",");
		for (int i = 0; i < n; i++) {
			order.add(Integer.parseInt(itemCnt[i]));
		}

		for (int i = 0; i < m; i++) {
			line = in.nextLine();
			String[] bomInput = line.split(",");
			List<Integer> bomDetail = new ArrayList<Integer>();

			for (int j = 1; j <= n; j++) {
				bomDetail.add(Integer.parseInt(bomInput[j]));
			}
			boms.put(bomInput[0], bomDetail);
		}
		in.close();

		Map<String, Integer> res = resolve(order, boms);

		System.out.println("match result:");
		for (String key : res.keySet()) {
			System.out.println(key + "*" + res.get(key));
		}
	}

	// write your code here
	// 每个商品的数量order 组合boms
	public static Map<String, Integer> resolve(List<Integer> order, Map<String, List<Integer>> boms) {
		int N = boms.size();
		int[] V = new int[N];
		List<String> list = new ArrayList<>(boms.keySet());
		List<List<Integer>> lists = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			lists.add(boms.get(list.get(i)));
			V[i] = countMax(lists.get(i), order);
		}
		List<Integer> sum = new ArrayList<>(order);
		DFS(V, sum, order, lists, 0, new ArrayList<>());
		Map<String, Integer> m = new HashMap<>();
		for (int i = 0; i < list.size() && r.get(i) > 0; i++) {
			m.put(list.get(i), r.get(i));
		}
		return m;

	}

	/**
	 * 深搜主函数，递归调用；
	 * 
	 * @param V
	 *            保留有所有组合方案最大套件数的数组
	 * @param sum
	 *            该方案下剩余所有种类产品数量信息数组，会动态改变
	 * @param order
	 *            所有种类产品总共数量，始终不变
	 * @param lists
	 *            所有组合方案的信息
	 * @param index
	 *            索引，指向目前轮到第几种组合方案出数量了
	 * @param result
	 *            保存所有组合方案的套件数的数组，当出一个方案时候就得把其中的信息赋值给成员变量r
	 */
	private static void DFS(int[] V, List<Integer> sum, List<Integer> order, List<List<Integer>> lists, int index,
			List<Integer> result) {
		if (index == V.length) {
			if (r == null || better(sum, result)) {
				r = new ArrayList<>(result);
				finalSum = new ArrayList<>(sum);
			}
			return;
		}

		for (int i = 0; i <= V[index]; i++) {
			if (!check(sum, lists.get(index), i)) {
				if (r == null || better(sum, result)) {
					r = new ArrayList<>(result);
					finalSum = new ArrayList<>(sum);
				}
				break;
			}
			result.add(i);
			List<Integer> tempSum = new ArrayList<>(sum);
			for (int j = 0; j < V.length; j++)
				tempSum.set(j, sum.get(j) - i * lists.get(index).get(j));
			DFS(V, tempSum, order, lists, index + 1, result);
			result.remove(result.size() - 1);
		}
	}

	/**
	 * 测试某种组合方案的套件组合信息result是否比已经认为是最好的r相比更好么 通过比较sum和result两者共同决定
	 * 
	 * @param sum
	 * @param result
	 * @return 如果更好，则返回true,否则false
	 */
	private static boolean better(List<Integer> sum, List<Integer> result) {
		int c1 = 0, c2 = 0; // 已用完商品的种类数 c1上一个方案   c2当前方案
		for (int i = 0; i < sum.size(); i++) {
			if (finalSum.get(i) == 0)
				c1++;
			if (sum.get(i) == 0)
				c2++;
		}
		if (c2 > c1)
			return true;
		else if (c1 == c2) { // 组合数 c1上一个方案   c2当前方案
			c1 = 0;
			c2 = 0;
			for (int i = 0; i < result.size(); i++) {
				if (r.get(i) == 0)
					c1++;
				if (result.get(i) == 0)
					c2++;
			}
			if (c2 < c1)
				return true;
		}
		return false;
	}

	/**
	 * 检查剩余所有产品数量信息数组sum是否还能匹配k套list组合方案； 例如sum:[2, 3, 1], list:[2, 2, 1], k =
	 * 2,此时就不匹配了，k为1才可以；
	 * 
	 * @param sum
	 * @param list
	 * @param k
	 * @return
	 */
	private static boolean check(List<Integer> sum, List<Integer> list, int k) {
		for (int i = 0; i < sum.size(); i++) {
			if (sum.get(i) < list.get(i) * k)
				return false;
		}
		return true;
	}

	/**
	 * 计算每一种组合方案的最大套数 例如 l:[1, 2, 1], order:[1, 3, 1], 传回的就是1
	 * 
	 * @param l
	 *            某一种组合方案
	 * @param order
	 *            总共所有产品各自数目数组
	 * @return 传回该组合方案的最大套数
	 */
	private static int countMax(List<Integer> l, List<Integer> order) {
		int k = -1;
		for (int i = 0; i < order.size(); i++) {
			if (l.get(i) == 0)
				continue;
			int temp = order.get(i) / l.get(i);
			if (k == -1)
				k = temp;
			else if (temp < k)
				k = temp;
		}
		return k;
	}
}
