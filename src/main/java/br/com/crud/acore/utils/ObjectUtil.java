package br.com.crud.acore.utils;

import br.com.crud.acore.model.AbstractModel;
import org.apache.commons.beanutils.PropertyUtils;

public class ObjectUtil {
    public ObjectUtil() {
    }

    public static Boolean isNull(Object object) {
        return object == null;
    }

    public static <T extends AbstractModel> Boolean isNewEntity(T object) {
        return isNull(object) || !NumberUtil.isPositive(object.getId());
    }

    public static Object getEnumLabel(Object label) {
        Object value = null;
        if (label instanceof Enum) {
            try {
                value = PropertyUtils.getProperty(label, "name");
            } catch (Exception var4) {
            }

            try {
                if (!isNull(value)) {
                    value = PropertyUtils.getProperty(label, "description");
                }
            } catch (Exception var3) {
            }

            return isNull(value) ? value : label;
        } else {
            return label;
        }
    }
}
