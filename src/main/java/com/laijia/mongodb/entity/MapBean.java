package com.laijia.mongodb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.laijia.mongodb.util.DataUtils;
import com.laijia.mongodb.util.JsonUtil;


public class MapBean extends HashMap<String, Object> implements Entity {

    private static final long serialVersionUID = 5819387600888005124L;

    public MapBean() {
        super();
    }

    /**
     * ����0��n���󣬴���MapBean
     *
     * @param args
     */
    public MapBean(Object... args) {
        super();
        puts(args);
    }

    /**
     * ����ȷ��֪����������ʱ������ʹ��
     *
     * @param key �Iֵ
     * @return
     */
    public <X> X get(String key) {
        try {
            X value = (X) super.get(key);
            return value;
        } catch (ClassCastException e) {
            throw new ClassCastException("MapBean�д�ŵ����ͣ��뷵�����Ͳ�ƥ��.");
        }

    }

    /**
     * ���Integer���͵�ֵ
     *
     * @param key ��ֵ
     * @return ֵ
     */
    public Integer getInt(Object key) {
        Object value = get(key);
        if (value != null) {
            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).intValue();
            } else if (value instanceof Long) {
                return ((Long) value).intValue();
            } else if (value instanceof Double) {
                return ((Double) value).intValue();
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            return (Integer) value;
        } else {
            return null;
        }
    }

    /**
     * ���Integer���͵�ֵ
     *
     * @param key ��ֵ
     * @return ֵ
     */
    public Long[] getLongArray(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object value = get(key);
        if (value == null) {
            value = get(key.toLowerCase());
        }
        if (value instanceof String[]) {
            return toLongs((String[]) value);
        } else if (value instanceof String) {
            String[] idsArray = StringUtils.split((String) value, ",");
            return toLongs((String[]) idsArray);
        } else {
            return null;
        }
    }

    /**
     * ����ָ����ֵ����long�����飬����ɾ��
     *
     * @param values
     * @return
     */
    private Long[] toLongs(String[] values) {
        if (values != null) {
            List<Long> ret = new LinkedList<Long>();
            for (int i = 0; i < values.length; i++) {
                Long t = DataUtils.toLong(values[i]);
                if (t != null) {
                    ret.add(t);
                }
            }
            return ret.toArray(new Long[ret.size()]);
        }
        return null;
    }

    /**
     * ���Byte���͵�ֵ
     *
     * @param key ��ֵ
     * @return ֵ
     */
    public Byte getByte(Object key) {
        Integer i = getInt(key);
        return i == null ? null : i.byteValue();
    }

    /**
     * ���Long���͵�ֵ
     *
     * @param key ��ֵ
     * @return ֵ
     */
    public Long getLong(Object key) {
        Object value = get(key);
        if (value != null) {
            if (value instanceof Long) {
                return (Long) this.get(key);
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).longValue();
            } else if (value instanceof String) {
                return Long.parseLong((String) value);
            }
        }
        return (Long) value;
    }

    /**
     * ���Double���͵�ֵ
     *
     * @param key ��ֵ
     * @return ֵ
     */
    public Double getDouble(Object key) {
        Object value = get(key);
        if (value != null) {
            if (value instanceof Double) {
                return ((Double) value);
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).doubleValue();
            } else if (value instanceof String) {
                return Double.parseDouble(((String) value).trim());
            }
        }
        return (Double) value;
    }

    /**
     * ���String���͵�ֵ
     *
     * @param key ��ֵ
     * @return ֵ
     */
    public String getString(Object key) {
        Object v = get(key);
        if (!(v instanceof String) && v != null) {
            return v.toString();
        }
        return (String) v;
    }

    /**
     * ���Byte���͵�ֵ
     *
     * @param key        ��ֵ
     * @param defaultVal ��ֵ�ǵ�Ĭ��ֵ
     * @return ֵ
     */
    public Byte getByte(Object key, byte defaultVal) {
        Byte b = getByte(key);
        return b == null ? defaultVal : b;
    }

    /**
     * ���int���͵�ֵ
     *
     * @param key        ��ֵ
     * @param defaultVal ��ֵ�ǵ�Ĭ��ֵ
     * @return ֵ
     */
    public int getInt(Object key, int defaultVal) {
        Integer i = getInt(key);
        return i == null ? defaultVal : i;
    }

    public boolean getBoolean(Object key) {
        return getBoolean(key, false);
    }

    /**
     * ����boolean����ֵ
     *
     * @param key
     * @param defaultVal
     * @return
     */
    public boolean getBoolean(Object key, boolean defaultVal) {
        Object v = get(key);
        if (v != null) {
            if (v instanceof Boolean) {
                return (Boolean) v;
            } else if (v instanceof String) {
                return "true".equals((String) v) || "yes".equals(v);
            } else if (v instanceof Number) {
                return ((Number) v).intValue() == 0;
            }
        } else {
            return defaultVal;
        }
        return false;
    }

    /**
     * ���long���͵�ֵ
     *
     * @param key        ��ֵ
     * @param defaultVal ��ֵ�ǵ�Ĭ��ֵ
     * @return ֵ
     */
    public long getLong(Object key, int defaultVal) {
        Long i = getLong(key);
        return i == null ? defaultVal : i;
    }

    /**
     * ���String���͵�ֵ
     *
     * @param key        ��ֵ
     * @param defaultVal ��ֵ�ǵ�Ĭ��ֵ
     * @return ֵ
     */
    public String getString(Object key, String defaultVal) {
        String value = getString(key);
        return value == null ? defaultVal : value;
    }

    /**
     * һ����Ӷ������Map��
     *
     * @param args
     */
    public void puts(Object... args) {
        for (int i = 1; i < args.length; i += 2) {
            put(String.valueOf(args[i - 1]), args[i]);
        }
    }

    /**
     * ��Mapת��json��ʽ���ַ���
     *
     * @return
     */
    public String toJson() {
        return JsonUtil.toJson(this);
    }

    /**
     * ��Map��ʽ��JSON�ַ���ֵ��ת��Map����
     *
     * @param keys
     * @return
     */
    public String toJson(Object... keys) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int i = 0; i < keys.length; i++) {
            if (this.containsKey(keys[i]))
                map.put(keys[i], this.get(keys[i]));
        }
        return JsonUtil.toJson(map);
    }

    public static MapBean toMapBean(Map map) {
        if (map != null) {
            MapBean r = new MapBean();
            for (Object key : map.keySet()) {
                r.put(String.valueOf(key), map.get(key));
            }
            return r;
        }
        return null;
    }

    public static MapBean toMapBean(Serializable e) {
        if (e != null) {
            String v = JsonUtil.toJson(e);
            return JsonUtil.toObject(v, MapBean.class);
        }
        return null;
    }

    public Map<String, String> toHashMap() {
    	HashMap<String, String> maps = new HashMap<String, String>();
        for (String s : this.keySet()) {
            maps.put(s, this.getString(s));
        }
        return maps;
    }

    public Serializable getId() {
        return this.getString("id");
    }
}