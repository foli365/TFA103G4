/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *     �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Campsite {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		
		if (columnName != null && "CAMP_NAME".equals(columnName)) { // �Ω�varchar
			aCondition = "(" + columnName + " like '%" + value + "%' OR LOCATION LIKE '%" + value + "%')";
		} else if (columnName != null && "CAMP_OPENING_TIME".equals(columnName)) {
			aCondition = columnName + " BETWEEN " + "'" + value.substring(0, 10) + "' AND '" + value.substring(13) + "'";
		} else if (columnName != null && "CAMP_PRICE".equals(columnName)) {
			String priceLow = value.substring(0, value.indexOf("~")); // �Ω�᭱��K
			aCondition = columnName + " BETWEEN " + priceLow + " AND " + value.substring(priceLow.length()+1);
		} else if (columnName != null && "EMPTY_CAMP_LEFT".equals(columnName)){
			aCondition = columnName + " > " + value;
		}
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("���e�X�d�߸�ƪ�����count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// �t�X req.getParameterMap()��k �^�� java.util.Map<java.lang.String,java.lang.String[]> ������
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("empno", new String[] { "7001" });
		map.put("ename", new String[] { "KING" });
		map.put("job", new String[] { "PRESIDENT" });
		map.put("hiredate", new String[] { "1981-11-17" });
		map.put("sal", new String[] { "5000.5" });
		map.put("comm", new String[] { "0.0" });
		map.put("deptno", new String[] { "10" });
		map.put("action", new String[] { "getXXX" }); // �`�NMap�̭��|�t��action��key

		String finalSQL = "select * from emp2 "
				          + jdbcUtil_CompositeQuery_Campsite.get_WhereCondition(map)
				          + "order by empno";
		System.out.println("����finalSQL = " + finalSQL);

	}
}
