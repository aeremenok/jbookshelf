package org.jbookshelf.model.db.cayenne;

import org.jbookshelf.model.db.cayenne.auto._JbsMap;

public class JbsMap extends _JbsMap {

    private static JbsMap instance;

    private JbsMap() {}

    public static JbsMap getInstance() {
        if(instance == null) {
            instance = new JbsMap();
        }

        return instance;
    }
}
