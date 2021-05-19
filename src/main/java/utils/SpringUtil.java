package utils;

import com.google.common.collect.Maps;
import ognl.Ognl;
import ognl.OgnlException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
 

/**
 * v2 u33
 * @author attilax
 *
 */
public class SpringUtil {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(getCfgFile());
		
	}

	public static String getDbUrlFrmSprbtCfg() {
		
		// TODO Auto-generated method stub  String propFilePath = "/cfg";
		//return PropertieUtil.getProperty(propFilePath,"db");;
//	String springcfgFiepath=	SpringUtil.getCfgFile();
		
		try {
			String cfgProperty = SpringUtil.getCfgProperty("spring.datasource.url");
		
			String username = SpringUtil.getCfgProperty("spring.datasource.username");
			String password = SpringUtil.getCfgProperty("spring.datasource.password");
			if(!cfgProperty.contains("?"))
				cfgProperty=cfgProperty+"?";
			cfgProperty=cfgProperty+"&user="+username+"&password="+password;
			return cfgProperty;
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}
	}

    public static String getCfgFile() throws Exception
    {
    	 String sprbtidx = getSprbtIdx();
        try{
            String profiles_active=  get_profiles_active();
            return "/"+sprbtidx+"-"+profiles_active+".yml";
        }catch( OgnlException e)
        {
            return "/"+sprbtidx+".yml";
        }
    }


    public static String get_profiles_active() throws Exception {
    	 String sprbtidx = getSprbtIdx();
	//	System.out.println(sprbtidx)
    	// com.kok.base.config.MybatisPlusConfigurer
    	
    	 String prpt = "spring.profiles.active";
        String f = "/"+sprbtidx+".yml";
		return getPrpt(prpt, f);
    }

    public static String getPrpt(   String prpt ,String ymlFile) throws OgnlException {
    	  org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
		Object mObject=yaml.load(SpringUtil.class.getResourceAsStream(ymlFile));
       
		@SuppressWarnings("unused")
		Object expression = Ognl.parseExpression(prpt);
      //  Ognl.get
        Object url = Ognl.getValue(prpt, mObject);
        return url.toString();
	}

	private static String getSprbtIdx() throws IOException {
		Properties properties = new Properties();
         InputStream inputStream = Object.class.getResourceAsStream("/cfg");
         
             properties.load(inputStream);
        
         String sprbtidx = properties.get("sprbtcfg_index").toString();
		return sprbtidx;
	}
//@Deprecated
//    public static Map getDbcfg() throws  Exception
//    {
//        String cfgTag=get_profiles_active();
//        org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
//        Object mObject=yaml.load(SpringUtil.class.getResourceAsStream("/application-"+cfgTag+".yml"));
//        Object expression = Ognl.parseExpression("spring.datasource.url");
//        Object url = Ognl.getValue(expression, mObject);
//        Object usr = Ognl.getValue(Ognl.parseExpression("spring.datasource.username"), mObject);
//        Object pwd = Ognl.getValue(Ognl.parseExpression("spring.datasource.password"), mObject);
//        if(pwd==null)pwd="";
//        Map m= Maps.newLinkedHashMap();
//        m.put("url",url);
//        m.put("user",usr.toString());
//        m.put("pwd",pwd.toString());
//        return m;
//    }

    public static String getCfgProperty(String k) throws Exception {
        try {
            org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
            String cfgFile = getCfgFile();
			Object mObject=yaml.load(SpringUtil.class.getResourceAsStream(cfgFile));

            String usr =   Ognl.getValue(Ognl.parseExpression(k), mObject).toString();
            return usr;
        }catch (Exception e){
            System.out.println("k:"+k);
            throw e;
        }


    }
}
