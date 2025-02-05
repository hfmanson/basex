package org.basex.query.func.fn;

import static org.basex.query.func.Function.*;

import org.basex.query.*;
import org.basex.query.CompileContext.*;
import org.basex.query.expr.*;
import org.basex.query.func.*;
import org.basex.query.iter.*;
import org.basex.query.util.*;
import org.basex.query.value.item.*;
import org.basex.query.value.type.*;
import org.basex.util.*;

/**
 * Function implementation.
 *
 * @author BaseX Team 2005-22, BSD License
 * @author Christian Gruen
 */
public final class FnCount extends StandardFunc {
  @Override
  public Int item(final QueryContext qc, final InputInfo ii) throws QueryException {
    // iterative access: if the iterator size is unknown, iterate through all results
    final Iter iter = exprs[0].iter(qc);
    long size = iter.size();
    if(size == -1) {
      do ++size; while(qc.next(iter) != null);
    }
    return Int.get(size);
  }

  @Override
  protected void simplifyArgs(final CompileContext cc) throws QueryException {
    exprs[0] = exprs[0].simplifyFor(Simplify.COUNT, cc);
  }

  @Override
  protected Expr opt(final CompileContext cc) throws QueryException {
    // return static result size
    final Expr expr = exprs[0];
    final long size = expr.size();
    if(size >= 0 && !expr.has(Flag.NDT)) return Int.get(size);

    // rewrite count(map:keys(...)) to map:size(...)
    if(_MAP_KEYS.is(expr))
      return cc.function(_MAP_SIZE, info, expr.args());
    // rewrite count(string-to-codepoints(...)) to string-length(...)
    if(STRING_TO_CODEPOINTS.is(expr) || _UTIL_CHARS.is(expr))
      return cc.function(STRING_LENGTH, info, expr.args());

    final Expr embedded = embed(cc, true);
    if(embedded != null) return embedded;

    return this;
  }

  @Override
  public Expr simplifyFor(final Simplify mode, final CompileContext cc) throws QueryException {
    if(mode == Simplify.EBV) {
      // if(count(nodes))  ->  if(nodes)
      // if(count(items))  ->  if(exists(items))
      final Expr expr = exprs[0];
      return cc.simplify(this, expr.seqType().type instanceof NodeType ? expr :
        cc.function(EXISTS, info, exprs));
    }
    return this;
  }
}
