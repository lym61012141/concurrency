/**
 * @author Liuym
 * @date 2019/1/12 0012
 */
public class Main {
    static int a = 1;
    static Integer b = 1;
//    static Person person = new Person();


    public static void main(String[] args) {
        Person person = new Person("aaa");
        // 仅仅是person 的指针地址赋值给p
        Person p= person;
        System.out.println(p.equals(person));
        // person 的指针地址发生变化，不会影响p的指针地址
        person = new Person("bbb");
        System.out.println(p.getName());
        /*person.setName("asdf");
        modifyPerson(person);
        System.out.println(person.getName());
        System.out.println(add(a));
        System.out.println(b);*/
    }

    static int add(int add) {
        a = add + 1;
        return a;
    }

    static void addInteger(Integer add) {
        add = add + 1;
//        return add;
    }

    static void modifyPerson(Person asdf) {
        asdf.setName("bbb");
        Person person1 = new Person();
        asdf = person1;
        asdf.setName("aaa");
    }
}
