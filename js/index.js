const {dom, flavor, rule, ruleset} = require('fathom-web');

function containsColonsOrDashes(elem) {
    return true;
}

// example partially from https://github.com/mozilla/fathom
var titleFinder = ruleset(
    // Give any title tag a score of 1, and tag it as title-ish:
    rule(dom("title"), node => [{score: 1, flavor: 'titley'}])
);

var titleNode = titleFinder.score(document).max('titley').element;
var title = titleNode.innerText;

java.setTitle(title);
