package org.basex.test.query;

import org.basex.core.AProp;

/**
 * XQuery Update Tests.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-09, ISC License
 * @author Lukas Kircher
 */
public class XQUPTest extends AbstractTest{
  
  /**
   * Constructor.
   */
  public XQUPTest() {
    doc =
      "<up>" +
      "<cars cat='c'>" +
        "<good>" +
          "<car id='1'>" +
            "<!-- NO COMMENT -->" +
          "</car>" +
          "<car id='2' color='blue'>" +
            "<wheels>optional</wheels>" +
          "</car>" +
        "</good>" +
        
        "<bad howbad='really bad'>" +
          "<car id='3' color='pink'>" +
            "<?advice nogo='buying this one'?>" +
            "<wheels>" +
              "positive" +
              "<count what='wheels'>2 and a half</count>" +
            "</wheels>" +
          "</car>" +
        "</bad>" +
        
        "<ugly>" +
          "<car id='4'>" +
            "<!-- nice one -->" +
          "</car>" +
        "</ugly>" +
      "</cars>" +
      "<?dohere what='how'?>" +
      "</up>";
    
    // XQUP expressions return an empty iterator thus every test query q which 
    // name starts with 'xxx' is an update query.
    // The test query following q represents the actual test.
    queries = new Object[][] {
//        { "xxxxxxxxxxxxx", nodes(),
//        "//processing-instruction('dohere')" },
        
        // delete elem attr txt pi com
        { "xxxdel1", nodes(),
        "delete nodes /up/cars/good/car[1]" },
        { "del1", nodes(5,12,22),
        "//car" },
        { "xxxdel2", nodes(),
        "delete nodes //car" },
        { "del2", nodes(),
        "//car" },
        { "xxxdel3", nodes(),
        "delete node /up/cars/good/car[1]/@id" },
        { "del3", nodes(),
        "/up/cars/good/car[1]/@id" },
        { "xxxdel4", nodes(),
        "delete node //wheels/text()" },
        { "del4", nodes(),
        "//wheels/text()" },
        { "xxxdel5", nodes(),
        "delete node //comment()" },
        { "del5", nodes(),
        "//comment()" },
        { "xxxdel6", nodes(),
        "delete node //processing-instruction()" },
        { "del6", nodes(),
        "//processing-instruction()" },
        
        // rename elem attr pi
        { "xxxren1", nodes(),
        "rename node /up/cars as 'CARS'" },
        { "ren1", nodes(2),
        "/up/CARS" },
        { "xxxren2", nodes(),
        "rename node /up/cars/good/car[1]/@id as 'ID'" },
        { "ren2", nodes(6),
        "//car/@ID" },
        { "xxxren3", nodes(),
        "rename node //processing-instruction('dohere') as 'BADVICE'" },
        { "ren3", nodes(28),
        "//processing-instruction('BADVICE')" },
        
        // replace
        
        // insert
        
        // parser tests
    };
  }
  @Override
  String details(final AProp prop) { return ""; }
 
  /*
  
  PRE DIS SIZ ATS  NS  KIND  CONTENT
  0   1  29   1   0  DOC   basex
  1   1  28   1   0  ELEM  up
  2   1  26   2   0  ELEM  cars
  3   1   1   1   0  ATTR  cat="c"
  4   2   9   1   0  ELEM  good
  5   1   3   2   0  ELEM  car
  6   1   1   1   0  ATTR  id="1"
  7   2   1   1   0  COMM   NO COMMENT 
  8   4   5   3   0  ELEM  car
  9   1   1   1   0  ATTR  id="2"
 10   2   1   1   0  ATTR  color="blue"
 11   3   2   1   0  ELEM  wheels
 12   1   1   1   0  TEXT  optional
 13  11  11   2   0  ELEM  bad
 14   1   1   1   0  ATTR  howbad="really bad"
 15   2   9   3   0  ELEM  car
 16   1   1   1   0  ATTR  id="3"
 17   2   1   1   0  ATTR  color="pink"
 18   3   1   1   0  PI    advice nogo='buying this one'
 19   4   5   1   0  ELEM  wheels
 20   1   1   1   0  TEXT  positive
 21   2   3   2   0  ELEM  count
 22   1   1   1   0  ATTR  what="wheels"
 23   2   1   1   0  TEXT  2 and a half
 24  22   4   1   0  ELEM  ugly
 25   1   3   2   0  ELEM  car
 26   1   1   1   0  ATTR  id="4"
 27   2   1   1   0  COMM   nice one 
 28  27   1   1   0  PI    dohere what='how'
  
  */
}
