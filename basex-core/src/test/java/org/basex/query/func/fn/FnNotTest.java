package org.basex.query.func.fn;

import org.basex.query.*;

/**
 * XQuery functions tests.
 *
 * @author BaseX Team 2005-18, BSD License
 * @author Christian Gruen
 */
public final class FnNotTest extends QueryTest {
  static {
    queries = new Object[][] {
      { "fn-notint1args-1", booleans(false), "fn:not(xs:int('-2147483648'))" },
      { "fn-notint1args-2", booleans(false), "fn:not(xs:int('-1873914410'))" },
      { "fn-notint1args-3", booleans(false), "fn:not(xs:int('2147483647'))" },
      { "fn-notintg1args-1", booleans(false),
        "fn:not(xs:integer('-999999999999999999'))" },
      { "fn-notintg1args-2", booleans(false),
        "fn:not(xs:integer('830993497117024304'))" },
      { "fn-notintg1args-3", booleans(false),
        "fn:not(xs:integer('999999999999999999'))" },
      { "fn-notdec1args-1", booleans(false),
        "fn:not(xs:decimal('-999999999999999999'))" },
      { "fn-notdec1args-2", booleans(false),
        "fn:not(xs:decimal('617375191608514839'))" },
      { "fn-notdec1args-3", booleans(false),
        "fn:not(xs:decimal('999999999999999999'))" },
      { "fn-notdbl1args-1", booleans(false),
        "fn:not(xs:double('-1.7976931348623157E308'))" },
      { "fn-notdbl1args-2", booleans(true),
        "fn:not(xs:double('0'))" },
      { "fn-notdbl1args-3", booleans(false),
        "fn:not(xs:double('1.7976931348623157E308'))" },
      { "fn-notflt1args-1", booleans(false),
        "fn:not(xs:float('-3.4028235E38'))" },
      { "fn-notflt1args-2", booleans(true),
        "fn:not(xs:float('0'))" },
      { "fn-notflt1args-3", booleans(false),
        "fn:not(xs:float('3.4028235E38'))" },
      { "fn-notlng1args-1", booleans(false),
        "fn:not(xs:long('-92233720368547758'))" },
      { "fn-notlng1args-2", booleans(false),
        "fn:not(xs:long('-47175562203048468'))" },
      { "fn-notlng1args-3", booleans(false),
        "fn:not(xs:long('92233720368547758'))" },
      { "fn-notusht1args-1", booleans(true),
        "fn:not(xs:unsignedShort('0'))" },
      { "fn-notusht1args-2", booleans(false),
        "fn:not(xs:unsignedShort('44633'))" },
      { "fn-notusht1args-3", booleans(false),
        "fn:not(xs:unsignedShort('65535'))" },
      { "fn-notnint1args-1", booleans(false),
        "fn:not(xs:negativeInteger('-999999999999999999'))" },
      { "fn-notnint1args-2", booleans(false),
        "fn:not(xs:negativeInteger('-297014075999096793'))" },
      { "fn-notnint1args-3", booleans(false),
        "fn:not(xs:negativeInteger('-1'))" },
      { "fn-notpint1args-1", booleans(false),
        "fn:not(xs:positiveInteger('1'))" },
      { "fn-notpint1args-2", booleans(false),
        "fn:not(xs:positiveInteger('52704602390610033'))" },
      { "fn-notpint1args-3", booleans(false),
        "fn:not(xs:positiveInteger('999999999999999999'))" },
      { "fn-notulng1args-1", booleans(true),
        "fn:not(xs:unsignedLong('0'))" },
      { "fn-notulng1args-2", booleans(false),
        "fn:not(xs:unsignedLong('130747108607674654'))" },
      { "fn-notulng1args-3", booleans(false),
        "fn:not(xs:unsignedLong('184467440737095516'))" },
      { "fn-notnpi1args-1", booleans(false),
        "fn:not(xs:nonPositiveInteger('-999999999999999999'))" },
      { "fn-notnpi1args-2", booleans(false),
        "fn:not(xs:nonPositiveInteger('-475688437271870490'))" },
      { "fn-notnpi1args-3", booleans(true),
        "fn:not(xs:nonPositiveInteger('0'))" },
      { "fn-notnni1args-1", booleans(true),
        "fn:not(xs:nonNegativeInteger('0'))" },
      { "fn-notnni1args-2", booleans(false),
        "fn:not(xs:nonNegativeInteger('303884545991464527'))" },
      { "fn-notnni1args-3", booleans(false),
        "fn:not(xs:nonNegativeInteger('999999999999999999'))" },
      { "fn-notsht1args-1", booleans(false), "fn:not(xs:short('-32768'))" },
      { "fn-notsht1args-2", booleans(false), "fn:not(xs:short('-5324'))" },
      { "fn-notsht1args-3", booleans(false), "fn:not(xs:short('32767'))" },

      { "fn-not-1", booleans(false), "fn:not('true')" },
      { "fn-not-2", booleans(false), "fn:not('fn:not()')" },
      { "fn-not-3", booleans(false), "fn:not('true') and fn:not('true')" },
      { "fn-not-4", booleans(false), "fn:not('true') or fn:not('true')" },
      { "fn-not-5", booleans(true), "fn:not('true') eq fn:not('true')" },
      { "fn-not-6", booleans(false), "fn:not('true') ne fn:not('true')" },
      { "fn-not-7", booleans(false), "fn:not('true') lt fn:not('true')" },
      { "fn-not-8", booleans(true), "fn:not('true') le fn:not('true')" },
      { "fn-not-9", booleans(false), "fn:not('true') gt fn:not('true')" },
      { "fn-not-10", booleans(true), "fn:not('true') ge fn:not('true')" },
      { "fn-not-11", booleans(true), "fn:not('true') = fn:not('true')" },
      { "fn-not-12", booleans(false), "fn:not('true') != fn:not('true')" },
      { "fn-not-13", booleans(false), "fn:not('true') < fn:not('true')" },
      { "fn-not-14", booleans(true), "fn:not('true') <= fn:not('true')" },
      { "fn-not-15", booleans(false), "fn:not('true') > fn:not('true')" },
      { "fn-not-16", booleans(true), "fn:not('true') >= fn:not('true')" },
      { "fn-not-17", booleans(false), "xs:boolean(fn:not('true'))" },
      { "fn-not-18", strings("false"), "fn:string(fn:not('true'))" },
      { "fn-not-19", strings("falsefalse"),
        "fn:concat(xs:string(fn:not('true')),xs:string(fn:not('true')))" },
      { "fn-not-20", booleans(true),
        "fn:contains(xs:string(fn:not('true')),xs:string(fn:not('true')))" },
      { "fn-not-21", integers(5), "fn:string-length(xs:string(fn:not('true')))" },

      { "K-NotFunc-1", "not()" },
      { "K-NotFunc-2", "not(1, 2, 3, 4, 5, 6)" },
      { "K-NotFunc-3", booleans(true), "not(false() and false())" },
      { "K-NotFunc-4", booleans(true), "not(not(true()))" },
      { "K-NotFunc-5", booleans(true), "not(false())" },
      { "K-NotFunc-6", booleans(true), "not(0)" },
      { "K-NotFunc-7", booleans(true), "not(())" },
      { "K-NotFunc-8", booleans(true), "not(xs:anyURI(''))" },
      { "K-NotFunc-9", booleans(true), "not(not(xs:anyURI('example.com/')))" },
      { "K-NotFunc-10", booleans(true),
        "not(fn:boolean((1, 2, 3, current-time())[1] treat as xs:integer)) " +
        "eq false()" },

      { "not  1", booleans(false), "not(<X/> = <X/>)" },
      { "not  2", booleans(true),  "not(<X/> != <X/>)" },
      { "not  3", booleans(false), "not(<X>a</X> < <X>b</X>)" },
      { "not  4", booleans(true),  "not(<X>a</X> > <X>b</X>)" },
      { "not  5", booleans(false), "not(<X>a</X> >= <X>a</X>)" },
      { "not  6", booleans(false), "not(<X>a</X> <= <X>a</X>)" },
      { "not  7", booleans(false), "not((<X>b</X>,<X>a</X>) <= <X>a</X>)" },
      { "not  8", booleans(false), "not((<X>b</X>,<X>a</X>) >= <X>a</X>)" },
      { "not  9", booleans(true),  "not(not(<X/>))" },
      { "not 10", booleans(false, true), "for $b in (true(),false()) return not($b)" },
      { "not 11", booleans(true, false), "for $b in (true(),false()) return not(not($b))" },

      { "not 12", booleans(false), "let $s := 1 let $e := 2 return not($s <= $e)" },
      { "not 13", booleans(true),  "let $s := 1 let $e := 2 return not($s >= $e)" },
      { "not 14", booleans(false), "let $s := 1 let $e := 2 return not($s <  $e)" },
      { "not 15", booleans(true),  "let $s := 1 let $e := 2 return not($s >  $e)" },
      { "not 16", booleans(true),  "let $s := 1 let $e := 2 return not($s =  $e)" },
      { "not 17", booleans(false), "let $s := 1 let $e := 2 return not($s != $e)" },
      { "not 18", booleans(false), "let $s := 1 let $e := 2 return not($s le $e)" },
      { "not 19", booleans(true),  "let $s := 1 let $e := 2 return not($s ge $e)" },
      { "not 20", booleans(false), "let $s := 1 let $e := 2 return not($s lt $e)" },
      { "not 21", booleans(true),  "let $s := 1 let $e := 2 return not($s gt $e)" },
      { "not 22", booleans(true),  "let $s := 1 let $e := 2 return not($s eq $e)" },
      { "not 23", booleans(false), "let $s := 1 let $e := 2 return not($s ne $e)" },
    };
  }
}
