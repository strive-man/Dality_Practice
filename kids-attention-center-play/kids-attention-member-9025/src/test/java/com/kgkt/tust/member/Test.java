// package com.kgkt.tust.member;
//
// import javax.annotation.Resource;
// import org.junit.runner.RunWith;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class Test {
//
////  @Resource
////  private ParentDao parentDao;
////  @org.junit.Test
////    public void test(){
////    ParentEntity parentEntity = parentDao.selectByParentAccount("15589346587");
////    System.out.println(parentEntity);
////    }
////
////  @org.junit.Test
////  public void test2() throws Exception {
//////    String publicKey = RsaUtil.generateKeyPair().getPublicKey();
//////    String privateKey = RsaUtil.generateKeyPair().getPrivateKey();
//////    System.out.println(publicKey);
//////    System.out.println(privateKey);
////// 公钥加密数据库
//////    String res =
// RsaUtil.encryptByPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh608C5XGQnqWkv5O4T7KNj+9kVOrMoP9PdaA43tP/Pk+9ajucMhh8E5WckLPwO8SIhJMWesLoa9bDavXkyXVNhy0zFTQh6Tk8QdkxL+MlDTv2j+9Hjnlurj2Ywn9bt4BCtJdwt+cG+pp4d895rT7ZgAMy+EIuWpVuFRqjzMTkOnaA5R74QWKkYmpL53Rtfn2U4addsbM6PSAShgDcgM5/qdx971vfDhVLV2WfSQ0SHnvB7UhfY1Y0xl6za+BAobmjdPTRESc0awUG6E8zI51pddXVQO4C/sZEyUsDZmOcil/4EorZuYjkTvphJsJo/1+UG9kZHkuR2kSSB0RjsgnVwIDAQAB", "12345");
//////    System.out.println(res);
////// 私钥解密数据库
//////    String s = RsaUtil.decryptByPrivateKey(
//////
// "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCpH6obrn2N8Qn5R9dPkFwAgH67ebZwgB5jRUpubCr6bxU2cpSKVJ9RB2EAp1LxSDKKGVXJvuUGFu8SYdz08xcDyqi2IWVs77PkkIKzyYLbeVb3ZgVMfWlMmtbpT6fACeqHdowZWhacYhPz0tikh3aAi0b0jMbmIBK+KVVfSfsFhUKIk8m+mabLkNjBCxvIJxocAQkelwQSAj4SM2J0cDxA5WKGzBTLtZcKfvVi57bDhzDIxeZFlr0GJvgDUvvLpUeGeAlwO+/58MhqSzDp6lqmTMA/oim7yImcmcIag+hUP0xc4Vot+FmUpRuvt2imJ0DVNyFOevl0lp7dFPW3WB7fAgMBAAECggEADJU6hsy4srLYVx+tOVI4RiFJTJGFrWEa4a/qFcUefXt+TIrB2njpUZIG+ZF5blI80GGKLHRzfbx/FRMK6X6cm7+cRdC+RxiefcbysoWvoryoZ8fDZSl/iB+NFdbDWFpkhKehTNGTFReG/IEXdUZO3sl+AfvnYH/KZTOWEnzinEpq52ictMbK9SpFxPtB7fqEp8+6Ic64JcZETDZAh4g0L39/hXYkgFf1B4DzRbIpUV6OQ26i9acuUwtjRgjAXeTcEXVeJninbBO7r+hBbIlQvplTXEG7oN5sNPa1sUyBhmbAGyNlV2UOZLNYWQ4+TVfvwjyYLm8VrToJP+JqKlOAgQKBgQDZBrCP24U1eeqU8hnoV60V7igH7xoq7Jry1TI8St0ghfpimUde++TykohuVAYxA2VRgtRyU7dsWLMFXrhCpRFvbJUdq/+Lpcron2BhJd0YwjZn5hvq0RS12meRVSPCXqP8IwzE3H0eaMVnELAA8H3iTJr2i3j3vr3NpeM1cN3Y0QKBgQDHfsNMgT4JyX/jqe1EmFrL/A4+oyhSgUKkyBsmsuGqjScdEY1tcDy5f2tbPMwnRX6BS5Du/pIIhyQ7prQCGDuJpXdq1HglE+4GPjb5hBDCoWfvePfdwaSmbdRjLCoHJUObus01MrG9SMOCaIAxjJers9ppmu9PZaOOH0ViXNxorwKBgQDSJ541z9bwb7xuuvnHeY9MGzB8blailjd9SmG47K2wXsyVSeRI4BdVpfujCo5sS+OCyPO8OcD2k/XXD1/SeVGm2jiTYQi2S7dvB1Ym/Kla3kYc2kDSY+BwMKv1mcJk54xYDgDBaNln5wCwywIEBt1/k7rDX0+mZ/gX+blI+KB64QKBgFHrnAcui2s5c2rpvKUOazqYpsDR/xziN5uzEHMzwbdnf0hQjOMD0+vYhx1QOgVmZCxz+3Ao2Y7m79oO+pxhlxFPAVq9JRkUtgmaQrjzom5KGYQdCKvneNVfBHg1Co0koD6y1tGhn5s65g4HG509Posjr+zt0peoBSG3daaOu3PBAoGAIq7DoYxYOBxJzTnOzpA4rMgZJ+7x7eeP3vWSTlV0PoZUJfDT8oyFBnHNpNnGxfOHYNVkP5DWDTL1fz72i5ROPjPVG6yYq21dd153+BuVt1LwbgJ9xX2/bvuluFK0pQbp5Zu+PKFhMDEpLXc21rkAEHLye6lE5UEvLasWcwfGs7s=",
//////
// "Iz5g0MP74nsk6bd5DJTIDCM5ncfDmUvaUnmYFDjCicFNbedM16LBexJIUNKPnRrFTUrTfm29wrbQxEeJC1guEZs4E2Uz+aXEhnOSD3HoaDYl+rh+Pbjzk7tt08VBzIoTStQxyHv6E4A6DtJUDcqNSXhbRw61EZg2c30U+UNjtMobHCAzaPkoELaGd6sYUCbR7aiznrYNuHVNOFupClEBJegzu2/L7oTXHQrawQamJEYydrJ9wyfUTYsPmIOhMGyfzJhQqBgymROMS33icPpIDOoh9STCiwIZk1Bu+tSecI+o9/wgSZB5km1ynhfC+unuuNXKKL9+HMzldpElcmZrqw==");
//////    System.out.println(s);
//// RsaUtil.test1(RsaUtil.generateKeyPair(),"12345");
////
////  }
////
////    @Resource
////    private StaffDao staffDao;
////      private KidDao kidDao;
//    @org.junit.Test
//    public void test3(){
////      StaffEntity staffEntity = staffDao.selectByStaffAccount("16611651802");
////      System.out.println(staffEntity);
////      KidEntity id = (KidEntity)kidDao.selectByKidParent("赵铁柱");
////      System.out.println(id.getKidId());
////      System.out.println(id.getClass());
//    }
// }
//
