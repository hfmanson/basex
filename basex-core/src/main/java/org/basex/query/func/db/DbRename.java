package org.basex.query.func.db;

import static org.basex.query.QueryError.*;
import static org.basex.util.Token.*;

import org.basex.core.cmd.*;
import org.basex.data.*;
import org.basex.io.*;
import org.basex.query.*;
import org.basex.query.up.*;
import org.basex.query.up.primitives.db.*;
import org.basex.query.up.primitives.node.*;
import org.basex.query.value.item.*;
import org.basex.query.value.seq.*;
import org.basex.util.*;
import org.basex.util.list.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-22, BSD License
 * @author Christian Gruen
 */
public final class DbRename extends DbAccess {
  @Override
  public Item item(final QueryContext qc, final InputInfo ii) throws QueryException {
    final Data data = toData(qc);
    final String source = toDbPath(1, qc), target = toDbPath(2, qc);
    if(!(Prop.CASE ? source.equals(target) : source.equalsIgnoreCase(target))) {
      // rename XML resources
      final Updates updates = qc.updates();
      final IntList docs = data.resources.docs(source);
      final int ds = docs.size();
      for(int d = 0; d < ds; d++) {
        final int pre = docs.get(d);
        final String trg = Rename.target(data, pre, source, target);
        if(trg.isEmpty() || Strings.endsWith(trg, '/') || Strings.endsWith(trg, '.'))
          throw DB_PATH_X.get(info, trg);
        updates.add(new ReplaceValue(pre, data, info, token(trg)), qc);
      }
      if(!data.inMemory()) {
        // rename binary resources
        final IOFile src = data.meta.binary(source), trg = data.meta.binary(target);
        if(src == null || trg == null) throw DB_PATH_X.get(info, src);
        if(!src.eq(trg)) {
          rename(data, src, trg, qc);
          updates.add(new DBDelete(data, src, info), qc);
        }
      }
    }
    return Empty.VALUE;
  }

  /**
   * Recursively creates rename operations for binary files.
   * @param data data reference
   * @param src source path
   * @param trg target path
   * @param qc query context
   * @throws QueryException query exception
   */
  private void rename(final Data data, final IOFile src, final IOFile trg, final QueryContext qc)
      throws QueryException {

    if(src.isDir()) {
      // dir  ->  file? error
      if(trg.exists() && !trg.isDir()) throw DB_PATH_X.get(info, src);
      // rename children
      for(final IOFile f : src.children()) rename(data, f, new IOFile(trg, f.name()), qc);
    } else if(src.exists()) {
      // file  ->  dir? error
      if(trg.isDir()) throw DB_PATH_X.get(info, src);
      qc.updates().add(new DBRename(data, src.path(), trg.path(), info), qc);
    }
  }
}
