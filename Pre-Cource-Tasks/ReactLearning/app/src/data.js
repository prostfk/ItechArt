// let mysql = require('mysql-json');


export default [
    {
        id:1,
        title: 'Java 11 roadmap: The new features you can expect',
        content: ' Now that Oracle has released Java Development Kit 10, the next version, JDK 11, is just around the corner. With a release candidate due in August 2018 and the production version in September 2018 as part of Oracle’s new six-month release cadence for the standard edition of Java, Version 11 will have 15 major feature changes.  Java 11 is also set to lose some capabilities through the removal of CORBA and Java EE (recently renamed Jakarta EE) modules, as well as the removal of JavaFX.  [ The new Java versions are here! Learn everything you need to know about what’s new in Java SE 10 and what’s new in Java EE 8. | Keep up with hot topics in programming with InfoWorld’s App Dev Report newsletter. ] Set to be a long-term support release unlike JDK 10, JDK 11 will be a reference implementation of Java Platform, Standard Edition (Java SE) 11. JDK 11 is set to receive premier-level support from Oracle until September 2023 and extended support, featuring patches and security alerts, until 2026. New LTS releases are due every three years, with JDK 17, due in 2021, slated to be the subsequent LTS release.   With the Java 11 feature plans now final, Oracle has forked the mainline repository, jdk/jdk, to the jdk/jdk11 stabilization repository. Changes pushed to jdk/jdk or jdk/client are now marked for JDK 12. The stabilization repository can accept select bug fixes and, if approved, late enhancements as per the JDK Release Process'
    },
    {
        id:2,
        title: 'Trump’s PAC wants to know which Space Force logo you like best',
        content: 'This morning, Vice President Pence detailed the administration’s plans to create a Space Force, and now the Trump PAC wants to know which logo to use for the new branch of the military. The Trump Make America Great Again Committee emailed supporters today asking them to vote on six different Space Force logos. The plan is to create a “new line of gear” based on one of the designs.  All six are stylized like the traditional mission patches that accompany most space launches. One of them seems to be the NASA logo but red and yellow. Another proclaims “Mars Awaits,” though the Space Force really has nothing to do with Mars. Instead, it’s about reorganizing the way the military procures satellites and trains personnel.Space Force has quickly become a major part of the Trump brand, and it looks like it will have its own line of merchandise akin to the Make America Great Again hats. Graphic designers have already thought about what the organization’s branding could look like. It’s a peppy way to commemorate a new bureaucratic reshuffling of the Defense Department. These badges seem more like merch than an official logo for a new branch of the military. We’ll have to keep waiting for that. '
    },
    {
        id:3,
        title: 'Oracle Says Drop Nashorn From JDKs',
        content: ' Oracle wants to deprecate the Nashorn JavaScript Engine and remove it from all future Java Development Kits. The details emerged as part of a JDK Enhancement Proposal.  The Nashorn JavaScript engine was introduced with Java 8 to replace the previous JavaScript scripting engine based on Mozilla Rhino. Nashorn is based on JSR 292, which adds support for dynamically typed languages on the Java platform; and on invokedynamic. Its aim was to provide better compliance with the ECMA normalized JavaScript specification and better runtime performance through invokedynamic-bound call sites.  Nashorn provides a way to invoke Java code from JavaScript, or to invoke JavaScript functions from Java code. Nashorn can be embedded in JavaScript applications as an interpreter, and its command-line tool, jjs, can be used to run JavaScript from the command line.  Nashorn  was a complete implementation of the ECMAScript-262 5.1 standard when it was released, but is now lagging behind due to the rapid pace at which ECMAScript language constructs, along with APIs, are adapted and modified.   Oracle says the only choices are either to deprecate the Nashorn JavaScript script engine and APIs, and the jjs tool, with the intent to remove them in a future release; or for  "a set of credible developers to express a clear desire to maintain Nashorn going forward. If that happens before this JEP is integrated then this JEP can be withdrawn. If that happens after this JEP is integrated, but before Nashorn is removed, then a follow-on JEP can revert the deprecation."  The problem with removing Nashorn is that certain applications will no longer run because of the expectation that JavaScript is present. Oracle says that:  "the breadth of Nashorn usage has not been easy to track. It is hoped that feedback for this JEP might provide better insight into actual Nashorn usage."  Responding to a tweet about the proposal,  Thomas Wuerthinger, Senior Research Director at Oracle Labs,said:  "The JavaScript implementation based on #GraalVM will be production-ready on all relevant platforms before Nashorn is deprecated. It will give you better performance and is fully compliant to the latest ECMAScript standard."  GraalVM is a virtual machine for running applications written in JavaScript, Python, Ruby, R, JVM-based languages like Java, Scala, Kotlin, and LLVM-based languages such as C and C++.'
    }
]

// let con = new MysqlJson({
//     host: "localhost",
//     user: "prostrmk",
//     password: "0",
//     database: "ItNews"
// });
// export default function (sql) {
//     con.connect(function (err) {
//         if (err) console.log(err.toString());
//         con.query(sql, function (err, response) {
//             console.log(response)
//         })
//     })
// }