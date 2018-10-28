package com.wechat.testSuit;

import com.wechat.department.DepartmentCreation;
import com.wechat.member.MemberCreation;
import com.wechat.message.MessageCreation;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DepartmentCreation.class,
        MemberCreation.class,
        MessageCreation.class
}
)
public class P0Suit {

}
