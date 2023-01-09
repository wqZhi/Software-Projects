public class test {
    public static void main(String[] args){
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(115);
        tree.insert(989);
        tree.insert(141);
        tree.insert(194);
        tree.insert(232);
        tree.insert(29);
        tree.insert(715);
        tree.insert(638);
        tree.insert(425);
        tree.insert(162);
        tree.insert(792);
        tree.insert(47);
        tree.insert(635);
        tree.insert(523);
        tree.insert(97);
        tree.insert(384);
        tree.insert(209);
        tree.insert(480);
        tree.insert(674);
        tree.insert(531);
        tree.insert(473);
        tree.insert(777);
        tree.insert(272);
        tree.insert(785);
        tree.insert(729);
        tree.insert(313);
        tree.insert(358);
        tree.insert(174);
        tree.insert(487);
        tree.insert(889);
        tree.insert(19);

        String expect = "[232, 141, 635, 47, 194, 425, 715, 29, 115, 162, 209, "
                + "313, 523, 638, 792, 19, 97, 174, 272, 384, 480, 531, 674, 777, "
                + "989, 358, 473, 487, 729, 785, 889]";

        System.out.println(tree.root.toString().equals(expect));
    }
}
