package cn.zucc.debug.macore.console.common;

public class MyError {

    /**
     * 操作成功
     */
    public static final int SUCCESS_CODE = 0;

    /**
     * 如果是删改查的操作表示操作对象不存在，增操作表示已存在
     */
    public static final int ERROR_CODE_ALREADY_OR_NOT_EXIST = 1;

    /**
     * 没有执行权限（如密码错误，操作其他用户的数据库等等）
     */
    public static final int ERROR_CODE_NOT_ACCESS = 2;

    /**
     * 远程数据库操作失败
     */
    public static final int ERROR_CODE_REMOTE_WRONG = 3;

    public static final String MESSAGE_REMOTE_WRONG = "remote db wrong";
    public static final String MESSAGE_ACCOUNT_NOT_EXIST = "account not exit";
    public static final String MESSAGE_WRONG_PASSWORD = "wrong password";
    public static final String MESSAGE_OBJECT_ALREADY_EXIST = "this object is already exit";
    public static final String MESSAGE_OBJECT_NOT_EXIST = "this object is not exit";
    public static final String MESSAGE_NO_ACCESS_TO_OBJECT_= "you are not access to control this object";
    public static final String MESSAGE_GROUP_ALREADY_EXIST = "this group already exit";
    public static final String MESSAGE_NO_ACCESS_TO_GROUP = "you are not access to control this group";
    public static final String MESSAGE_GROUP_NOT_EXIST = "this group is not exit";



}
