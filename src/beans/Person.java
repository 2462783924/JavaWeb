package beans;

public class Person {
    private int id;
    private String nick;
    private String password;
    private String stuno;
    private String stuname;
    private String sex;
    private String age;
    private String phone;
    private String email;
    private String pic;
    private String intpoduce;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntpoduce() {
        return intpoduce;
    }

    public void setIntpoduce(String intpoduce) {
        this.intpoduce = intpoduce;
    }

    public Person() {
    }

    public Person(int id, String nick, String password, String stuno, String stuname, String sex, String age, String phone, String email, String pic, String intpoduce) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.stuno = stuno;
        this.stuname = stuname;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.pic = pic;
        this.intpoduce = intpoduce;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", stuno='" + stuno + '\'' +
                ", stuname='" + stuname + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", pic='" + pic + '\'' +
                ", introduce='" + intpoduce + '\'' +
                '}';
    }
}
