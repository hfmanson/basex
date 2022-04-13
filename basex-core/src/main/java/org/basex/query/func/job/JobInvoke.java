package org.basex.query.func.job;

import org.basex.query.*;
import org.basex.query.value.item.*;
import org.basex.util.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-22, BSD License
 * @author Christian Gruen
 */
public final class JobInvoke extends JobEval {
  @Override
  public Str item(final QueryContext qc, final InputInfo ii) throws QueryException {
    return eval(toContent(toToken(exprs[0], qc), qc), qc);
  }
}
