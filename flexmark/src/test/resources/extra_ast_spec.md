---
title: Core Extra Test Spec
author: Vladimir Schneider
version: 0.1
date: '2016-06-06'
license: '[CC-BY-SA 4.0](http://creativecommons.org/licenses/by-sa/4.0/)'
...

---

## Extra tests

Should be ignored

```````````````````````````````` example(Extra tests: 1) options(IGNORE)
```markdown
---
```
.
<pre><code class="language-markdown">---
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[3] close:[16, 19, "```"]
````````````````````````````````


Should fail

```````````````````````````````` example(Extra tests: 2) options(FAIL)
```markdown
abc
```
.
<pre><code class="language-markdown">---
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[3] close:[16, 19, "```"]
````````````````````````````````


Code fence starting with setext header marker

```````````````````````````````` example Extra tests: 3
```markdown
---
```
.
<pre><code class="language-markdown">---
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[3] close:[16, 19, "```"]
````````````````````````````````


```````````````````````````````` example Extra tests: 4
```markdown
===
```
.
<pre><code class="language-markdown">===
</code></pre>
.
Document[0, 20]
  FencedCodeBlock[0, 19] open:[0, 3, "```"] info:[3, 11, "markdown"] content:[12, 16] lines[3] close:[16, 19, "```"]
````````````````````````````````


Make sure indentation is properly implemented

```````````````````````````````` example Extra tests: 5
> - item 1
> - item 2
>     1. item 1
>     2. item 2
.
<blockquote>
  <ul>
    <li>item 1</li>
    <li>item 2
      <ol>
        <li>item 1</li>
        <li>item 2</li>
      </ol>
    </li>
  </ul>
</blockquote>
.
Document[0, 54]
  BlockQuote[0, 54] marker:[0, 1, ">"]
    BulletList[2, 54] isTight
      BulletListItem[2, 11] open:[2, 3, "-"] isTight
        Paragraph[4, 11]
          Text[4, 10] chars:[4, 10, "item 1"]
      BulletListItem[13, 54] open:[13, 14, "-"] isTight
        Paragraph[15, 22]
          Text[15, 21] chars:[15, 21, "item 2"]
        OrderedList[28, 54] isTight delimiter:'.'
          OrderedListItem[28, 38] open:[28, 30, "1."] isTight
            Paragraph[31, 38]
              Text[31, 37] chars:[31, 37, "item 1"]
          OrderedListItem[44, 54] open:[44, 46, "2."] isTight
            Paragraph[47, 54]
              Text[47, 53] chars:[47, 53, "item 2"]
````````````````````````````````


Make sure indentation is properly implemented

```````````````````````````````` example Extra tests: 6
> - item 1
>
> - item 2
>
>     1. item 1
>
>     2. item 2
.
<blockquote>
  <ul>
    <li>
      <p>item 1</p>
    </li>
    <li>
      <p>item 2</p>
      <ol>
        <li>
          <p>item 1</p>
        </li>
        <li>
          <p>item 2</p>
        </li>
      </ol>
    </li>
  </ul>
</blockquote>
.
Document[0, 60]
  BlockQuote[0, 60] marker:[0, 1, ">"]
    BulletList[2, 60] isLoose
      BulletListItem[2, 11] open:[2, 3, "-"] isLoose
        Paragraph[4, 11]
          Text[4, 10] chars:[4, 10, "item 1"]
      BulletListItem[15, 60] open:[15, 16, "-"] isLoose
        Paragraph[17, 24]
          Text[17, 23] chars:[17, 23, "item 2"]
        OrderedList[32, 60] isLoose delimiter:'.'
          OrderedListItem[32, 42] open:[32, 34, "1."] isLoose
            Paragraph[35, 42]
              Text[35, 41] chars:[35, 41, "item 1"]
          OrderedListItem[50, 60] open:[50, 52, "2."] isLoose
            Paragraph[53, 60]
              Text[53, 59] chars:[53, 59, "item 2"]
````````````````````````````````


## Reference Repository Keep First tests

Test repository KEEP_FIRST behavior, meaning the first reference def is used

```````````````````````````````` example Reference Repository Keep First tests: 1
[ref]

[ref]: /url1
[ref]: /url2
[ref]: /url3
.
<p><a href="/url1">ref</a></p>
.
Document[0, 46]
  Paragraph[0, 6]
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[7, 19] refOpen:[7, 8, "["] ref:[8, 11, "ref"] refClose:[11, 13, "]:"] url:[14, 19, "/url1"]
  Reference[20, 32] refOpen:[20, 21, "["] ref:[21, 24, "ref"] refClose:[24, 26, "]:"] url:[27, 32, "/url2"]
  Reference[33, 45] refOpen:[33, 34, "["] ref:[34, 37, "ref"] refClose:[37, 39, "]:"] url:[40, 45, "/url3"]
````````````````````````````````


## Reference Repository Keep Last tests

Test repository KEEP_LAST behavior, meaning the last reference def is used

```````````````````````````````` example(Reference Repository Keep Last tests: 1) options(keep-last)
[ref]

[ref]: /url1
[ref]: /url2
[ref]: /url3
.
<p><a href="/url3">ref</a></p>
.
Document[0, 46]
  Paragraph[0, 6]
    LinkRef[0, 5] referenceOpen:[0, 1, "["] reference:[1, 4, "ref"] referenceClose:[4, 5, "]"]
      Text[1, 4] chars:[1, 4, "ref"]
  Reference[7, 19] refOpen:[7, 8, "["] ref:[8, 11, "ref"] refClose:[11, 13, "]:"] url:[14, 19, "/url1"]
  Reference[20, 32] refOpen:[20, 21, "["] ref:[21, 24, "ref"] refClose:[24, 26, "]:"] url:[27, 32, "/url2"]
  Reference[33, 45] refOpen:[33, 34, "["] ref:[34, 37, "ref"] refClose:[37, 39, "]:"] url:[40, 45, "/url3"]
````````````````````````````````


## Heading options

Allow atx headers without a space between # and the title

```````````````````````````````` example(Heading options: 1) options(hdr-no-atx-space)
#Heading
##Heading
###Heading
####Heading
#####Heading
######Heading

#Heading #
##Heading #
###Heading #
####Heading #
#####Heading #
######Heading #
.
<h1>Heading</h1>
<h2>Heading</h2>
<h3>Heading</h3>
<h4>Heading</h4>
<h5>Heading</h5>
<h6>Heading</h6>
<h1>Heading</h1>
<h2>Heading</h2>
<h3>Heading</h3>
<h4>Heading</h4>
<h5>Heading</h5>
<h6>Heading</h6>
.
Document[0, 151]
  Heading[0, 8] textOpen:[0, 1, "#"] text:[1, 8, "Heading"]
    Text[1, 8] chars:[1, 8, "Heading"]
  Heading[9, 18] textOpen:[9, 11, "##"] text:[11, 18, "Heading"]
    Text[11, 18] chars:[11, 18, "Heading"]
  Heading[19, 29] textOpen:[19, 22, "###"] text:[22, 29, "Heading"]
    Text[22, 29] chars:[22, 29, "Heading"]
  Heading[30, 41] textOpen:[30, 34, "####"] text:[34, 41, "Heading"]
    Text[34, 41] chars:[34, 41, "Heading"]
  Heading[42, 54] textOpen:[42, 47, "#####"] text:[47, 54, "Heading"]
    Text[47, 54] chars:[47, 54, "Heading"]
  Heading[55, 68] textOpen:[55, 61, "######"] text:[61, 68, "Heading"]
    Text[61, 68] chars:[61, 68, "Heading"]
  Heading[70, 80] textOpen:[70, 71, "#"] text:[71, 78, "Heading"] textClose:[79, 80, "#"]
    Text[71, 78] chars:[71, 78, "Heading"]
  Heading[81, 92] textOpen:[81, 83, "##"] text:[83, 90, "Heading"] textClose:[91, 92, "#"]
    Text[83, 90] chars:[83, 90, "Heading"]
  Heading[93, 105] textOpen:[93, 96, "###"] text:[96, 103, "Heading"] textClose:[104, 105, "#"]
    Text[96, 103] chars:[96, 103, "Heading"]
  Heading[106, 119] textOpen:[106, 110, "####"] text:[110, 117, "Heading"] textClose:[118, 119, "#"]
    Text[110, 117] chars:[110, 117, "Heading"]
  Heading[120, 134] textOpen:[120, 125, "#####"] text:[125, 132, "Heading"] textClose:[133, 134, "#"]
    Text[125, 132] chars:[125, 132, "Heading"]
  Heading[135, 150] textOpen:[135, 141, "######"] text:[141, 148, "Heading"] textClose:[149, 150, "#"]
    Text[141, 148] chars:[141, 148, "Heading"]
````````````````````````````````


Don't allow leading spaces

```````````````````````````````` example(Heading options: 2) options(hdr-no-lead-space)
 # Heading
 ## Heading
 ### Heading
 #### Heading
 ##### Heading
 ###### Heading
.
<p># Heading
## Heading
### Heading
#### Heading
##### Heading
###### Heading</p>
.
Document[0, 81]
  Paragraph[1, 81]
    Text[1, 10] chars:[1, 10, "# Heading"]
    SoftLineBreak[10, 11]
    Text[12, 22] chars:[12, 22, "## Heading"]
    SoftLineBreak[22, 23]
    Text[24, 35] chars:[24, 35, "### H … ading"]
    SoftLineBreak[35, 36]
    Text[37, 49] chars:[37, 49, "####  … ading"]
    SoftLineBreak[49, 50]
    Text[51, 64] chars:[51, 64, "##### … ading"]
    SoftLineBreak[64, 65]
    Text[66, 80] chars:[66, 80, "##### … ading"]
````````````````````````````````


Don't allow leading spaces, don't require atx marker space

```````````````````````````````` example(Heading options: 3) options(hdr-no-lead-space, hdr-no-atx-space)
 #Heading
 ##Heading
 ###Heading
 ####Heading
 #####Heading
 ######Heading
.
<p>#Heading
##Heading
###Heading
####Heading
#####Heading
######Heading</p>
.
Document[0, 75]
  Paragraph[1, 75]
    Text[1, 9] chars:[1, 9, "#Heading"]
    SoftLineBreak[9, 10]
    Text[11, 20] chars:[11, 20, "##Heading"]
    SoftLineBreak[20, 21]
    Text[22, 32] chars:[22, 32, "###Heading"]
    SoftLineBreak[32, 33]
    Text[34, 45] chars:[34, 45, "####H … ading"]
    SoftLineBreak[45, 46]
    Text[47, 59] chars:[47, 59, "##### … ading"]
    SoftLineBreak[59, 60]
    Text[61, 74] chars:[61, 74, "##### … ading"]
````````````````````````````````


Minimum setext marker length 3

```````````````````````````````` example(Heading options: 4) options(setext-marker-length)
Not a Heading 1
=
 
Not a Heading 1
==
 
Heading 1
===
 
 
Not a Heading 2
-
 
Not a Heading 2
--
 
Heading 2
---
 
.
<p>Not a Heading 1
=</p>
<p>Not a Heading 1
==</p>
<h1>Heading 1</h1>
<p>Not a Heading 2
-</p>
<p>Not a Heading 2
--</p>
<h2>Heading 2</h2>
.
Document[0, 116]
  Paragraph[0, 18]
    Text[0, 15] chars:[0, 15, "Not a … ing 1"]
    SoftLineBreak[15, 16]
    Text[16, 17] chars:[16, 17, "="]
  Paragraph[20, 39]
    Text[20, 35] chars:[20, 35, "Not a … ing 1"]
    SoftLineBreak[35, 36]
    Text[36, 38] chars:[36, 38, "=="]
  Heading[41, 54] text:[41, 50, "Heading 1"] textClose:[51, 54, "==="]
    Text[41, 50] chars:[41, 50, "Heading 1"]
  Paragraph[59, 77]
    Text[59, 74] chars:[59, 74, "Not a … ing 2"]
    SoftLineBreak[74, 75]
    Text[75, 76] chars:[75, 76, "-"]
  Paragraph[79, 98]
    Text[79, 94] chars:[79, 94, "Not a … ing 2"]
    SoftLineBreak[94, 95]
    Text[95, 97] chars:[95, 97, "--"]
  Heading[100, 113] text:[100, 109, "Heading 2"] textClose:[110, 113, "---"]
    Text[100, 109] chars:[100, 109, "Heading 2"]
````````````````````````````````


## List Options

### List - Fixed Indent

Options defined: list-fixed-indent, list-no-break, list-no-loose, list-no-start to change the
behavior of list parser and renderer

Default processing

```````````````````````````````` example List - Fixed Indent: 1
* item 1
 * item 2
  * item 3
   * item 4
    *  item 5
     *  item 6
      *  item 7
       *  item 8
        *  item 9
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4</li>
  <li>item 5</li>
  <li>item 6</li>
  <li>item 7</li>
  <li>item 8</li>
  <li>item 9</li>
</ul>
.
Document[0, 122]
  BulletList[0, 122] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[10, 19] open:[10, 11, "*"] isTight
      Paragraph[12, 19]
        Text[12, 18] chars:[12, 18, "item 2"]
    BulletListItem[21, 30] open:[21, 22, "*"] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 3"]
    BulletListItem[33, 42] open:[33, 34, "*"] isTight
      Paragraph[35, 42]
        Text[35, 41] chars:[35, 41, "item 4"]
    BulletListItem[46, 56] open:[46, 47, "*"] isTight
      Paragraph[49, 56]
        Text[49, 55] chars:[49, 55, "item 5"]
    BulletListItem[61, 71] open:[61, 62, "*"] isTight
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 6"]
    BulletListItem[77, 87] open:[77, 78, "*"] isTight
      Paragraph[80, 87]
        Text[80, 86] chars:[80, 86, "item 7"]
    BulletListItem[94, 104] open:[94, 95, "*"] isTight
      Paragraph[97, 104]
        Text[97, 103] chars:[97, 103, "item 8"]
    BulletListItem[112, 122] open:[112, 113, "*"] isTight
      Paragraph[115, 122]
        Text[115, 121] chars:[115, 121, "item 9"]
````````````````````````````````


Indent less than list's item content indent is a list item, >= is a sub item

```````````````````````````````` example(List - Fixed Indent: 2) options(list-content-indent)
* item 1
 * item 2
  * item 3
   * item 4
    * item 5
     * item 6
      * item 7
       * item 8
        * item 9
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>item 3</li>
      <li>item 4
        <ul>
          <li>item 5</li>
          <li>item 6
            <ul>
              <li>item 7</li>
              <li>item 8
                <ul>
                  <li>item 9</li>
                </ul>
              </li>
            </ul>
          </li>
        </ul>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 117]
  BulletList[0, 117] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[10, 117] open:[10, 11, "*"] isTight
      Paragraph[12, 19]
        Text[12, 18] chars:[12, 18, "item 2"]
      BulletList[21, 117] isTight
        BulletListItem[21, 30] open:[21, 22, "*"] isTight
          Paragraph[23, 30]
            Text[23, 29] chars:[23, 29, "item 3"]
        BulletListItem[33, 117] open:[33, 34, "*"] isTight
          Paragraph[35, 42]
            Text[35, 41] chars:[35, 41, "item 4"]
          BulletList[46, 117] isTight
            BulletListItem[46, 55] open:[46, 47, "*"] isTight
              Paragraph[48, 55]
                Text[48, 54] chars:[48, 54, "item 5"]
            BulletListItem[60, 117] open:[60, 61, "*"] isTight
              Paragraph[62, 69]
                Text[62, 68] chars:[62, 68, "item 6"]
              BulletList[75, 117] isTight
                BulletListItem[75, 84] open:[75, 76, "*"] isTight
                  Paragraph[77, 84]
                    Text[77, 83] chars:[77, 83, "item 7"]
                BulletListItem[91, 117] open:[91, 92, "*"] isTight
                  Paragraph[93, 100]
                    Text[93, 99] chars:[93, 99, "item 8"]
                  BulletList[108, 117] isTight
                    BulletListItem[108, 117] open:[108, 109, "*"] isTight
                      Paragraph[110, 117]
                        Text[110, 116] chars:[110, 116, "item 9"]
````````````````````````````````


Indent less than list's item content indent + 4 is a list item, >= is a sub item

- kramdown 1.2.0

```````````````````````````````` example(List - Fixed Indent: 3) options(list-content-indent, list-content-indent-offset-2)
* item 1
 * item 2
  * item 3
   * item 4
    * item 5
     * item 6
      * item 7
       * item 8
        * item 9
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4
  * item 5
    <ul>
      <li>item 6</li>
      <li>item 7</li>
      <li>item 8</li>
      <li>item 9</li>
    </ul>
  </li>
</ul>
.
Document[0, 117]
  BulletList[0, 117] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[10, 19] open:[10, 11, "*"] isTight
      Paragraph[12, 19]
        Text[12, 18] chars:[12, 18, "item 2"]
    BulletListItem[21, 30] open:[21, 22, "*"] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 3"]
    BulletListItem[33, 117] open:[33, 34, "*"] isTight
      Paragraph[35, 55]
        Text[35, 41] chars:[35, 41, "item 4"]
        SoftLineBreak[41, 42]
        Text[46, 54] chars:[46, 54, "* item 5"]
      BulletList[60, 117] isTight
        BulletListItem[60, 69] open:[60, 61, "*"] isTight
          Paragraph[62, 69]
            Text[62, 68] chars:[62, 68, "item 6"]
        BulletListItem[75, 84] open:[75, 76, "*"] isTight
          Paragraph[77, 84]
            Text[77, 83] chars:[77, 83, "item 7"]
        BulletListItem[91, 100] open:[91, 92, "*"] isTight
          Paragraph[93, 100]
            Text[93, 99] chars:[93, 99, "item 8"]
        BulletListItem[108, 117] open:[108, 109, "*"] isTight
          Paragraph[110, 117]
            Text[110, 116] chars:[110, 116, "item 9"]
````````````````````````````````


Indent less than list's item content indent + 4 is a list item, >= is a sub item

- showdown 0.3.1
- Markdown.pl 1.0.1
- Markdown.pl 1.0.2b8
- RedCarpet 3.1.2
- PHP Markdown 1.0.2
- PHP Markdown Extra 1.2.8
- Parsedown 1.6.0
- s9e\TextFormatter (Fatdown/PHP)

```````````````````````````````` example(List - Fixed Indent: 4) options(list-content-indent, list-over-indents-to-first-item, list-content-indent-overrides-code, list-content-indent-offset-2)
* item 1
 * item 2
  * item 3
   * item 4
    * item 5
     * item 6
      * item 7
       * item 8
        * item 9
.
<ul>
  <li>item 1
    <ul>
      <li>item 2</li>
      <li>item 3</li>
      <li>item 4</li>
      <li>item 5
        <ul>
          <li>item 6</li>
          <li>item 7</li>
          <li>item 8</li>
          <li>item 9</li>
        </ul>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 117]
  BulletList[0, 117] isTight
    BulletListItem[0, 117] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
      BulletList[10, 117] isTight
        BulletListItem[10, 19] open:[10, 11, "*"] isTight
          Paragraph[12, 19]
            Text[12, 18] chars:[12, 18, "item 2"]
        BulletListItem[21, 30] open:[21, 22, "*"] isTight
          Paragraph[23, 30]
            Text[23, 29] chars:[23, 29, "item 3"]
        BulletListItem[33, 42] open:[33, 34, "*"] isTight
          Paragraph[35, 42]
            Text[35, 41] chars:[35, 41, "item 4"]
        BulletListItem[46, 117] open:[46, 47, "*"] isTight
          Paragraph[48, 55]
            Text[48, 54] chars:[48, 54, "item 5"]
          BulletList[60, 117] isTight
            BulletListItem[60, 69] open:[60, 61, "*"] isTight
              Paragraph[62, 69]
                Text[62, 68] chars:[62, 68, "item 6"]
            BulletListItem[75, 84] open:[75, 76, "*"] isTight
              Paragraph[77, 84]
                Text[77, 83] chars:[77, 83, "item 7"]
            BulletListItem[91, 100] open:[91, 92, "*"] isTight
              Paragraph[93, 100]
                Text[93, 99] chars:[93, 99, "item 8"]
            BulletListItem[108, 117] open:[108, 109, "*"] isTight
              Paragraph[110, 117]
                Text[110, 116] chars:[110, 116, "item 9"]
````````````````````````````````


Fixed indentation only (4 spaces)

- pandoc (strict) 1.17.2
- pandoc 1.17.2
- lunamark 0.4.0
- RDiscount 2.1.7
- Python-Markdown 2.6.5
- Minima 0.8.0a3_20140907
- MultiMarkdown 5.1.0
- pegdown

```````````````````````````````` example(List - Fixed Indent: 5) options(list-fixed-indent)
* item 1
 * item 2
  * item 3
   * item 4
    *  item 5
     *  item 6
      *  item 7
       *  item 8
        *  item 9
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4
    <ul>
      <li>item 5</li>
      <li>item 6</li>
      <li>item 7</li>
      <li>item 8
        <ul>
          <li>item 9</li>
        </ul>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 122]
  BulletList[0, 122] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[10, 19] open:[10, 11, "*"] isTight
      Paragraph[12, 19]
        Text[12, 18] chars:[12, 18, "item 2"]
    BulletListItem[21, 30] open:[21, 22, "*"] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 3"]
    BulletListItem[33, 122] open:[33, 34, "*"] isTight
      Paragraph[35, 42]
        Text[35, 41] chars:[35, 41, "item 4"]
      BulletList[46, 122] isTight
        BulletListItem[46, 56] open:[46, 47, "*"] isTight
          Paragraph[49, 56]
            Text[49, 55] chars:[49, 55, "item 5"]
        BulletListItem[61, 71] open:[61, 62, "*"] isTight
          Paragraph[64, 71]
            Text[64, 70] chars:[64, 70, "item 6"]
        BulletListItem[77, 87] open:[77, 78, "*"] isTight
          Paragraph[80, 87]
            Text[80, 86] chars:[80, 86, "item 7"]
        BulletListItem[94, 122] open:[94, 95, "*"] isTight
          Paragraph[97, 104]
            Text[97, 103] chars:[97, 103, "item 8"]
          BulletList[112, 122] isTight
            BulletListItem[112, 122] open:[112, 113, "*"] isTight
              Paragraph[115, 122]
                Text[115, 121] chars:[115, 121, "item 9"]
````````````````````````````````


Fixed indentation with code blocks

- pegdown
- pandoc (strict) 1.17.2
- pandoc 1.17.2
- lunamark 0.4.0
- MultiMarkdown 5.1.0

```````````````````````````````` example(List - Fixed Indent: 6) options(list-fixed-indent)
* item 1
    
    this is not code
    
        this is code
 * item 2
    
    this is not code
    
        this is code
  * item 3
    
    this is not code
    
        this is code
   * item 4
    
    this is not code
    
        this is code
    * item 5
    
        this is not code
    
            this is code
     * item 6
    
        this is not code
    
            this is code
      * item 7
    
        this is not code
    
            this is code
       * item 8
    
        this is not code
    
            this is code
        * item 9
    
            this is not code
        
                this is code
.
<ul>
  <li>
    <p>item 1</p>
    <p>this is not code</p>
    <pre><code>this is code
</code></pre>
  </li>
  <li>
    <p>item 2</p>
    <p>this is not code</p>
    <pre><code>this is code
</code></pre>
  </li>
  <li>
    <p>item 3</p>
    <p>this is not code</p>
    <pre><code>this is code
</code></pre>
  </li>
  <li>
    <p>item 4</p>
    <p>this is not code</p>
    <pre><code>this is code
</code></pre>
    <ul>
      <li>
        <p>item 5</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
      </li>
      <li>
        <p>item 6</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
      </li>
      <li>
        <p>item 7</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
      </li>
      <li>
        <p>item 8</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
        <ul>
          <li>
            <p>item 9</p>
            <p>this is not code</p>
            <pre><code>this is code
</code></pre>
          </li>
        </ul>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 637]
  BulletList[0, 637] isLoose
    BulletListItem[0, 61] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
      Paragraph[18, 35]
        Text[18, 34] chars:[18, 34, "this  …  code"]
      IndentedCodeBlock[48, 61]
    BulletListItem[62, 123] open:[62, 63, "*"] isLoose
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 2"]
      Paragraph[80, 97]
        Text[80, 96] chars:[80, 96, "this  …  code"]
      IndentedCodeBlock[110, 123]
    BulletListItem[125, 186] open:[125, 126, "*"] isLoose
      Paragraph[127, 134]
        Text[127, 133] chars:[127, 133, "item 3"]
      Paragraph[143, 160]
        Text[143, 159] chars:[143, 159, "this  …  code"]
      IndentedCodeBlock[173, 186]
    BulletListItem[189, 637] open:[189, 190, "*"] isLoose
      Paragraph[191, 198]
        Text[191, 197] chars:[191, 197, "item 4"]
      Paragraph[207, 224]
        Text[207, 223] chars:[207, 223, "this  …  code"]
      IndentedCodeBlock[237, 250]
      BulletList[254, 637] isLoose
        BulletListItem[254, 323] open:[254, 255, "*"] isLoose
          Paragraph[256, 263]
            Text[256, 262] chars:[256, 262, "item 5"]
          Paragraph[276, 293]
            Text[276, 292] chars:[276, 292, "this  …  code"]
          IndentedCodeBlock[310, 323]
        BulletListItem[328, 397] open:[328, 329, "*"] isLoose
          Paragraph[330, 337]
            Text[330, 336] chars:[330, 336, "item 6"]
          Paragraph[350, 367]
            Text[350, 366] chars:[350, 366, "this  …  code"]
          IndentedCodeBlock[384, 397]
        BulletListItem[403, 472] open:[403, 404, "*"] isLoose
          Paragraph[405, 412]
            Text[405, 411] chars:[405, 411, "item 7"]
          Paragraph[425, 442]
            Text[425, 441] chars:[425, 441, "this  …  code"]
          IndentedCodeBlock[459, 472]
        BulletListItem[479, 637] open:[479, 480, "*"] isLoose
          Paragraph[481, 488]
            Text[481, 487] chars:[481, 487, "item 8"]
          Paragraph[501, 518]
            Text[501, 517] chars:[501, 517, "this  …  code"]
          IndentedCodeBlock[535, 548]
          BulletList[556, 637] isLoose
            BulletListItem[556, 637] open:[556, 557, "*"] isLoose
              Paragraph[558, 565]
                Text[558, 564] chars:[558, 564, "item 9"]
              Paragraph[582, 599]
                Text[582, 598] chars:[582, 598, "this  …  code"]
              IndentedCodeBlock[624, 637]
````````````````````````````````


parent list content indent

```````````````````````````````` example(List - Fixed Indent: 7) options(list-content-indent)
* item 1
    
    this is not code
    
        this is code
 * item 2
    
    this is not code
    
        this is code
  * item 3
    
    this is not code
    
        this is code
   * item 4
    
    this is not code
    
        this is code
    * item 5
    
        this is not code
    
            this is code
     * item 6
    
        this is not code
    
            this is code
      * item 7
    
        this is not code
    
            this is code
       * item 8
    
        this is not code
    
            this is code
        * item 9
    
            this is not code
        
                this is code
.
<ul>
  <li>
    <p>item 1</p>
    <p>this is not code</p>
    <pre><code>  this is code
</code></pre>
  </li>
  <li>
    <p>item 2</p>
    <p>this is not code</p>
    <pre><code> this is code
</code></pre>
    <ul>
      <li>item 3</li>
    </ul>
    <p>this is not code</p>
    <pre><code> this is code
</code></pre>
    <ul>
      <li>item 4</li>
    </ul>
    <p>this is not code</p>
    <pre><code> this is code
</code></pre>
    <ul>
      <li>
        <p>item 5</p>
        <p>this is not code</p>
        <pre><code>  this is code
</code></pre>
      </li>
      <li>
        <p>item 6</p>
        <p>this is not code</p>
        <pre><code> this is code
</code></pre>
        <ul>
          <li>item 7</li>
        </ul>
        <p>this is not code</p>
        <pre><code> this is code
</code></pre>
        <ul>
          <li>item 8</li>
        </ul>
        <p>this is not code</p>
        <pre><code> this is code
</code></pre>
        <ul>
          <li>
            <p>item 9</p>
            <p>this is not code</p>
            <pre><code>  this is code
</code></pre>
          </li>
        </ul>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 637]
  BulletList[0, 637] isLoose
    BulletListItem[0, 61] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
      Paragraph[18, 35]
        Text[18, 34] chars:[18, 34, "this  …  code"]
      IndentedCodeBlock[46, 61]
    BulletListItem[62, 637] open:[62, 63, "*"] isLoose
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 2"]
      Paragraph[80, 97]
        Text[80, 96] chars:[80, 96, "this  …  code"]
      IndentedCodeBlock[109, 123]
      BulletList[125, 134] isTight
        BulletListItem[125, 134] open:[125, 126, "*"] isTight
          Paragraph[127, 134]
            Text[127, 133] chars:[127, 133, "item 3"]
      Paragraph[143, 160]
        Text[143, 159] chars:[143, 159, "this  …  code"]
      IndentedCodeBlock[172, 186]
      BulletList[189, 198] isTight
        BulletListItem[189, 198] open:[189, 190, "*"] isTight
          Paragraph[191, 198]
            Text[191, 197] chars:[191, 197, "item 4"]
      Paragraph[207, 224]
        Text[207, 223] chars:[207, 223, "this  …  code"]
      IndentedCodeBlock[236, 250]
      BulletList[254, 637] isLoose
        BulletListItem[254, 323] open:[254, 255, "*"] isLoose
          Paragraph[256, 263]
            Text[256, 262] chars:[256, 262, "item 5"]
          Paragraph[276, 293]
            Text[276, 292] chars:[276, 292, "this  …  code"]
          IndentedCodeBlock[308, 323]
        BulletListItem[328, 637] open:[328, 329, "*"] isLoose
          Paragraph[330, 337]
            Text[330, 336] chars:[330, 336, "item 6"]
          Paragraph[350, 367]
            Text[350, 366] chars:[350, 366, "this  …  code"]
          IndentedCodeBlock[383, 397]
          BulletList[403, 412] isTight
            BulletListItem[403, 412] open:[403, 404, "*"] isTight
              Paragraph[405, 412]
                Text[405, 411] chars:[405, 411, "item 7"]
          Paragraph[425, 442]
            Text[425, 441] chars:[425, 441, "this  …  code"]
          IndentedCodeBlock[458, 472]
          BulletList[479, 488] isTight
            BulletListItem[479, 488] open:[479, 480, "*"] isTight
              Paragraph[481, 488]
                Text[481, 487] chars:[481, 487, "item 8"]
          Paragraph[501, 518]
            Text[501, 517] chars:[501, 517, "this  …  code"]
          IndentedCodeBlock[534, 548]
          BulletList[556, 637] isLoose
            BulletListItem[556, 637] open:[556, 557, "*"] isLoose
              Paragraph[558, 565]
                Text[558, 564] chars:[558, 564, "item 9"]
              Paragraph[582, 599]
                Text[582, 598] chars:[582, 598, "this  …  code"]
              IndentedCodeBlock[622, 637]
````````````````````````````````


Indent less than list's item content indent + 4 is a list item, >= is a sub item

```````````````````````````````` example(List - Fixed Indent: 8) options(list-content-indent, list-content-indent-offset-2)
* item 1
    
    this is not code
    
        this is code
 * item 2
    
    this is not code
    
        this is code
  * item 3
    
    this is not code
    
        this is code
   * item 4
    
    this is not code
    
        this is code
    * item 5
    
        this is not code
    
            this is code
     * item 6
    
        this is not code
    
            this is code
      * item 7
    
        this is not code
    
            this is code
       * item 8
    
        this is not code
    
            this is code
        * item 9
    
            this is not code
        
                this is code
.
<ul>
  <li>
    <p>item 1</p>
    <p>this is not code</p>
    <pre><code>  this is code
</code></pre>
  </li>
  <li>
    <p>item 2</p>
    <p>this is not code</p>
    <pre><code> this is code
</code></pre>
  </li>
  <li>
    <p>item 3</p>
    <p>this is not code</p>
    <pre><code>this is code
</code></pre>
  </li>
  <li>
    <p>item 4</p>
    <pre><code>this is not code
</code></pre>
    <p>this is code
    * item 5</p>
    <p>this is not code</p>
    <pre><code>   this is code
</code></pre>
    <ul>
      <li>
        <p>item 6</p>
        <p>this is not code</p>
        <pre><code> this is code
</code></pre>
      </li>
      <li>
        <p>item 7</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
      </li>
      <li>
        <p>item 8</p>
      </li>
    </ul>
    <p>this is not code</p>
    <pre><code>   this is code
</code></pre>
    <ul>
      <li>
        <p>item 9</p>
        <p>this is not code</p>
        <pre><code>  this is code
</code></pre>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 637]
  BulletList[0, 637] isLoose
    BulletListItem[0, 61] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
      Paragraph[18, 35]
        Text[18, 34] chars:[18, 34, "this  …  code"]
      IndentedCodeBlock[46, 61]
    BulletListItem[62, 123] open:[62, 63, "*"] isLoose
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 2"]
      Paragraph[80, 97]
        Text[80, 96] chars:[80, 96, "this  …  code"]
      IndentedCodeBlock[109, 123]
    BulletListItem[125, 186] open:[125, 126, "*"] isLoose
      Paragraph[127, 134]
        Text[127, 133] chars:[127, 133, "item 3"]
      Paragraph[143, 160]
        Text[143, 159] chars:[143, 159, "this  …  code"]
      IndentedCodeBlock[173, 186]
    BulletListItem[189, 637] open:[189, 190, "*"] isLoose
      Paragraph[191, 198]
        Text[191, 197] chars:[191, 197, "item 4"]
      IndentedCodeBlock[207, 229]
      Paragraph[237, 263]
        Text[237, 249] chars:[237, 249, "this  …  code"]
        SoftLineBreak[249, 250]
        Text[254, 262] chars:[254, 262, "* item 5"]
      Paragraph[276, 293]
        Text[276, 292] chars:[276, 292, "this  …  code"]
      IndentedCodeBlock[307, 323]
      BulletList[328, 488] isLoose
        BulletListItem[328, 397] open:[328, 329, "*"] isLoose
          Paragraph[330, 337]
            Text[330, 336] chars:[330, 336, "item 6"]
          Paragraph[350, 367]
            Text[350, 366] chars:[350, 366, "this  …  code"]
          IndentedCodeBlock[383, 397]
        BulletListItem[403, 472] open:[403, 404, "*"] isLoose
          Paragraph[405, 412]
            Text[405, 411] chars:[405, 411, "item 7"]
          Paragraph[425, 442]
            Text[425, 441] chars:[425, 441, "this  …  code"]
          IndentedCodeBlock[459, 472]
        BulletListItem[479, 488] open:[479, 480, "*"] isLoose
          Paragraph[481, 488]
            Text[481, 487] chars:[481, 487, "item 8"]
      Paragraph[501, 518]
        Text[501, 517] chars:[501, 517, "this  …  code"]
      IndentedCodeBlock[532, 548]
      BulletList[556, 637] isLoose
        BulletListItem[556, 637] open:[556, 557, "*"] isLoose
          Paragraph[558, 565]
            Text[558, 564] chars:[558, 564, "item 9"]
          Paragraph[582, 599]
            Text[582, 598] chars:[582, 598, "this  …  code"]
          IndentedCodeBlock[622, 637]
````````````````````````````````


Indent less than list's item content indent + 4 is a list item, >= is a sub item

```````````````````````````````` example(List - Fixed Indent: 9) options(list-content-indent, list-over-indents-to-first-item, list-content-indent-overrides-code, list-content-indent-offset-2)
* item 1
    
    this is not code
    
        this is code
 * item 2
    
    this is not code
    
        this is code
  * item 3
    
    this is not code
    
        this is code
   * item 4
    
    this is not code
    
        this is code
    * item 5
    
        this is not code
    
            this is code
     * item 6
    
        this is not code
    
            this is code
      * item 7
    
        this is not code
    
            this is code
       * item 8
    
        this is not code
    
            this is code
        * item 9
    
            this is not code
        
                this is code
.
<ul>
  <li>
    <p>item 1</p>
    <p>this is not code</p>
    <pre><code>  this is code
</code></pre>
    <ul>
      <li>item 2</li>
    </ul>
    <p>this is not code</p>
    <pre><code>  this is code
</code></pre>
    <ul>
      <li>
        <p>item 3</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
      </li>
      <li>
        <p>item 4</p>
      </li>
    </ul>
    <p>this is not code</p>
    <pre><code>  this is code
</code></pre>
    <ul>
      <li>
        <p>item 5</p>
        <p>this is not code</p>
        <pre><code>  this is code
</code></pre>
      </li>
      <li>
        <p>item 6</p>
        <p>this is not code</p>
        <pre><code> this is code
</code></pre>
      </li>
      <li>
        <p>item 7</p>
        <p>this is not code</p>
        <pre><code>this is code
</code></pre>
      </li>
      <li>
        <p>item 8</p>
        <pre><code>  this is not code
</code></pre>
        <p>this is code
        * item 9</p>
        <p>this is not code</p>
        <pre><code>   this is code
</code></pre>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 637]
  BulletList[0, 637] isLoose
    BulletListItem[0, 637] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
      Paragraph[18, 35]
        Text[18, 34] chars:[18, 34, "this  …  code"]
      IndentedCodeBlock[46, 61]
      BulletList[62, 71] isTight
        BulletListItem[62, 71] open:[62, 63, "*"] isTight
          Paragraph[64, 71]
            Text[64, 70] chars:[64, 70, "item 2"]
      Paragraph[80, 97]
        Text[80, 96] chars:[80, 96, "this  …  code"]
      IndentedCodeBlock[108, 123]
      BulletList[125, 198] isLoose
        BulletListItem[125, 186] open:[125, 126, "*"] isLoose
          Paragraph[127, 134]
            Text[127, 133] chars:[127, 133, "item 3"]
          Paragraph[143, 160]
            Text[143, 159] chars:[143, 159, "this  …  code"]
          IndentedCodeBlock[173, 186]
        BulletListItem[189, 198] open:[189, 190, "*"] isLoose
          Paragraph[191, 198]
            Text[191, 197] chars:[191, 197, "item 4"]
      Paragraph[207, 224]
        Text[207, 223] chars:[207, 223, "this  …  code"]
      IndentedCodeBlock[235, 250]
      BulletList[254, 637] isLoose
        BulletListItem[254, 323] open:[254, 255, "*"] isLoose
          Paragraph[256, 263]
            Text[256, 262] chars:[256, 262, "item 5"]
          Paragraph[276, 293]
            Text[276, 292] chars:[276, 292, "this  …  code"]
          IndentedCodeBlock[308, 323]
        BulletListItem[328, 397] open:[328, 329, "*"] isLoose
          Paragraph[330, 337]
            Text[330, 336] chars:[330, 336, "item 6"]
          Paragraph[350, 367]
            Text[350, 366] chars:[350, 366, "this  …  code"]
          IndentedCodeBlock[383, 397]
        BulletListItem[403, 472] open:[403, 404, "*"] isLoose
          Paragraph[405, 412]
            Text[405, 411] chars:[405, 411, "item 7"]
          Paragraph[425, 442]
            Text[425, 441] chars:[425, 441, "this  …  code"]
          IndentedCodeBlock[459, 472]
        BulletListItem[479, 637] open:[479, 480, "*"] isLoose
          Paragraph[481, 488]
            Text[481, 487] chars:[481, 487, "item 8"]
          IndentedCodeBlock[499, 523]
          Paragraph[535, 565]
            Text[535, 547] chars:[535, 547, "this  …  code"]
            SoftLineBreak[547, 548]
            Text[556, 564] chars:[556, 564, "* item 9"]
          Paragraph[582, 599]
            Text[582, 598] chars:[582, 598, "this  …  code"]
          IndentedCodeBlock[621, 637]
````````````````````````````````


### List - No Auto Loose

With auto loose setting for list

```````````````````````````````` example List - No Auto Loose: 1
* item 1
* item 2
* item 3
    * sub item 1
    
    * sub item 2
    
    * sub item 3
* item 4
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3
    <ul>
      <li>
        <p>sub item 1</p>
      </li>
      <li>
        <p>sub item 2</p>
      </li>
      <li>
        <p>sub item 3</p>
      </li>
    </ul>
  </li>
  <li>item 4</li>
</ul>
.
Document[0, 97]
  BulletList[0, 97] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 88] open:[18, 19, "*"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
      BulletList[31, 88] isLoose
        BulletListItem[31, 44] open:[31, 32, "*"] isLoose
          Paragraph[33, 44]
            Text[33, 43] chars:[33, 43, "sub item 1"]
        BulletListItem[53, 66] open:[53, 54, "*"] isLoose
          Paragraph[55, 66]
            Text[55, 65] chars:[55, 65, "sub item 2"]
        BulletListItem[75, 88] open:[75, 76, "*"] isLoose
          Paragraph[77, 88]
            Text[77, 87] chars:[77, 87, "sub item 3"]
    BulletListItem[88, 97] open:[88, 89, "*"] isTight
      Paragraph[90, 97]
        Text[90, 96] chars:[90, 96, "item 4"]
````````````````````````````````


Without auto loose setting for list

```````````````````````````````` example(List - No Auto Loose: 2) options(list-no-loose)
* item 1
* item 2
* item 3
    * sub item 1
    
    * sub item 2
    
    * sub item 3
* item 4
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3
    <ul>
      <li>
        <p>sub item 1</p>
      </li>
      <li>
        <p>sub item 2</p>
      </li>
      <li>sub item 3</li>
    </ul>
  </li>
  <li>item 4</li>
</ul>
.
Document[0, 97]
  BulletList[0, 97] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 88] open:[18, 19, "*"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
      BulletList[31, 88] isTight
        BulletListItem[31, 44] open:[31, 32, "*"] isLoose
          Paragraph[33, 44]
            Text[33, 43] chars:[33, 43, "sub item 1"]
        BulletListItem[53, 66] open:[53, 54, "*"] isLoose
          Paragraph[55, 66]
            Text[55, 65] chars:[55, 65, "sub item 2"]
        BulletListItem[75, 88] open:[75, 76, "*"] isTight
          Paragraph[77, 88]
            Text[77, 87] chars:[77, 87, "sub item 3"]
    BulletListItem[88, 97] open:[88, 89, "*"] isTight
      Paragraph[90, 97]
        Text[90, 96] chars:[90, 96, "item 4"]
````````````````````````````````


### List - No Auto Loose, Loose Item if Previous Loose

Without auto loose setting for list with loose if previous loose item

```````````````````````````````` example(List - No Auto Loose, Loose Item if Previous Loose: 1) options(list-no-loose, list-loose-if-prev)
* item 1
* item 2
* item 3

* item 4
* item 5

* item 6

* item 7

.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>
    <p>item 3</p>
  </li>
  <li>
    <p>item 4</p>
  </li>
  <li>
    <p>item 5</p>
  </li>
  <li>
    <p>item 6</p>
  </li>
  <li>
    <p>item 7</p>
  </li>
</ul>
.
Document[0, 67]
  BulletList[0, 66] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 27] open:[18, 19, "*"] isLoose
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
    BulletListItem[28, 37] open:[28, 29, "*"] isLoose
      Paragraph[30, 37]
        Text[30, 36] chars:[30, 36, "item 4"]
    BulletListItem[37, 46] open:[37, 38, "*"] isLoose
      Paragraph[39, 46]
        Text[39, 45] chars:[39, 45, "item 5"]
    BulletListItem[47, 56] open:[47, 48, "*"] isLoose
      Paragraph[49, 56]
        Text[49, 55] chars:[49, 55, "item 6"]
    BulletListItem[57, 66] open:[57, 58, "*"] isLoose
      Paragraph[59, 66]
        Text[59, 65] chars:[59, 65, "item 7"]
````````````````````````````````


Without auto loose setting for list with loose if previous loose item

```````````````````````````````` example(List - No Auto Loose, Loose Item if Previous Loose: 2) options(list-no-loose, list-loose-if-prev)
* main item 
    * item 1
    * item 2
    * item 3
    
    * item 4
    * item 5
    
    * item 6
    
    * item 7

.
<ul>
  <li>main item
    <ul>
      <li>item 1</li>
      <li>item 2</li>
      <li>
        <p>item 3</p>
      </li>
      <li>
        <p>item 4</p>
      </li>
      <li>
        <p>item 5</p>
      </li>
      <li>
        <p>item 6</p>
      </li>
      <li>
        <p>item 7</p>
      </li>
    </ul>
  </li>
</ul>
.
Document[0, 120]
  BulletList[0, 119] isTight
    BulletListItem[0, 119] open:[0, 1, "*"] isTight
      Paragraph[2, 13]
        Text[2, 11] chars:[2, 11, "main item"]
      BulletList[17, 119] isTight
        BulletListItem[17, 26] open:[17, 18, "*"] isTight
          Paragraph[19, 26]
            Text[19, 25] chars:[19, 25, "item 1"]
        BulletListItem[30, 39] open:[30, 31, "*"] isTight
          Paragraph[32, 39]
            Text[32, 38] chars:[32, 38, "item 2"]
        BulletListItem[43, 52] open:[43, 44, "*"] isLoose
          Paragraph[45, 52]
            Text[45, 51] chars:[45, 51, "item 3"]
        BulletListItem[61, 70] open:[61, 62, "*"] isLoose
          Paragraph[63, 70]
            Text[63, 69] chars:[63, 69, "item 4"]
        BulletListItem[74, 83] open:[74, 75, "*"] isLoose
          Paragraph[76, 83]
            Text[76, 82] chars:[76, 82, "item 5"]
        BulletListItem[92, 101] open:[92, 93, "*"] isLoose
          Paragraph[94, 101]
            Text[94, 100] chars:[94, 100, "item 6"]
        BulletListItem[110, 119] open:[110, 111, "*"] isLoose
          Paragraph[112, 119]
            Text[112, 118] chars:[112, 118, "item 7"]
````````````````````````````````


### List - No Break on Double Blank Line

With break all lists on two blank lines

```````````````````````````````` example(List - No Break on Double Blank Line: 1) options(list-break)
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
</ul>
<ul>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 80]
  BulletList[0, 52] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight
          Paragraph[41, 52]
            Text[41, 51] chars:[41, 51, "sub item 2"]
  BulletList[62, 80] isTight
    BulletListItem[62, 71] open:[62, 63, "*"] isTight
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 4"]
    BulletListItem[71, 80] open:[71, 72, "*"] isTight
      Paragraph[73, 80]
        Text[73, 79] chars:[73, 79, "item 5"]
````````````````````````````````


Without break all lists on two blank lines

```````````````````````````````` example List - No Break on Double Blank Line: 2
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
* item 4
* item 5
.
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
  <li>
    <p>item 4</p>
  </li>
  <li>
    <p>item 5</p>
  </li>
</ul>
.
Document[0, 80]
  BulletList[0, 80] isLoose
    BulletListItem[0, 9] open:[0, 1, "*"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isLoose
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight
          Paragraph[41, 52]
            Text[41, 51] chars:[41, 51, "sub item 2"]
    BulletListItem[62, 71] open:[62, 63, "*"] isLoose
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 4"]
    BulletListItem[71, 80] open:[71, 72, "*"] isLoose
      Paragraph[73, 80]
        Text[73, 79] chars:[73, 79, "item 5"]
````````````````````````````````


Without break all lists on two blank lines no auto loose

```````````````````````````````` example(List - No Break on Double Blank Line: 3) options(list-no-loose, list-no-break)
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>
    <p>item 2</p>
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 80]
  BulletList[0, 80] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isLoose
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight
          Paragraph[41, 52]
            Text[41, 51] chars:[41, 51, "sub item 2"]
    BulletListItem[62, 71] open:[62, 63, "*"] isTight
      Paragraph[64, 71]
        Text[64, 70] chars:[64, 70, "item 4"]
    BulletListItem[71, 80] open:[71, 72, "*"] isTight
      Paragraph[73, 80]
        Text[73, 79] chars:[73, 79, "item 5"]
````````````````````````````````


With break all lists on two blank lines

```````````````````````````````` example(List - No Break on Double Blank Line: 4) options(list-break)
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
    * sub item 3
    * sub item 4
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>sub item 1</li>
      <li>sub item 2</li>
    </ul>
  </li>
</ul>
<pre><code>* sub item 3
* sub item 4
</code></pre>
<ul>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 114]
  BulletList[0, 52] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 52] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 52] isTight
        BulletListItem[22, 35] open:[22, 23, "*"] isTight
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isTight
          Paragraph[41, 52]
            Text[41, 51] chars:[41, 51, "sub item 2"]
  IndentedCodeBlock[66, 96]
  BulletList[96, 114] isTight
    BulletListItem[96, 105] open:[96, 97, "*"] isTight
      Paragraph[98, 105]
        Text[98, 104] chars:[98, 104, "item 4"]
    BulletListItem[105, 114] open:[105, 106, "*"] isTight
      Paragraph[107, 114]
        Text[107, 113] chars:[107, 113, "item 5"]
````````````````````````````````


Without break on two blank lines

```````````````````````````````` example List - No Break on Double Blank Line: 5
* item 1
* item 2
    * sub item 1
    * sub item 2
    
    
    * sub item 3
    * sub item 4
* item 4
* item 5
.
<ul>
  <li>item 1</li>
  <li>item 2
    <ul>
      <li>
        <p>sub item 1</p>
      </li>
      <li>
        <p>sub item 2</p>
      </li>
      <li>
        <p>sub item 3</p>
      </li>
      <li>
        <p>sub item 4</p>
      </li>
    </ul>
  </li>
  <li>item 4</li>
  <li>item 5</li>
</ul>
.
Document[0, 114]
  BulletList[0, 114] isTight
    BulletListItem[0, 9] open:[0, 1, "*"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 96] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
      BulletList[22, 96] isLoose
        BulletListItem[22, 35] open:[22, 23, "*"] isLoose
          Paragraph[24, 35]
            Text[24, 34] chars:[24, 34, "sub item 1"]
        BulletListItem[39, 52] open:[39, 40, "*"] isLoose
          Paragraph[41, 52]
            Text[41, 51] chars:[41, 51, "sub item 2"]
        BulletListItem[66, 79] open:[66, 67, "*"] isLoose
          Paragraph[68, 79]
            Text[68, 78] chars:[68, 78, "sub item 3"]
        BulletListItem[83, 96] open:[83, 84, "*"] isLoose
          Paragraph[85, 96]
            Text[85, 95] chars:[85, 95, "sub item 4"]
    BulletListItem[96, 105] open:[96, 97, "*"] isTight
      Paragraph[98, 105]
        Text[98, 104] chars:[98, 104, "item 4"]
    BulletListItem[105, 114] open:[105, 106, "*"] isTight
      Paragraph[107, 114]
        Text[107, 113] chars:[107, 113, "item 5"]
````````````````````````````````


### List - No Bullet Match

With bullet matching for items within a list

```````````````````````````````` example List - No Bullet Match: 1
- item 1
* item 1
+ item 1
.
<ul>
  <li>item 1</li>
</ul>
<ul>
  <li>item 1</li>
</ul>
<ul>
  <li>item 1</li>
</ul>
.
Document[0, 27]
  BulletList[0, 9] isTight
    BulletListItem[0, 9] open:[0, 1, "-"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
  BulletList[9, 18] isTight
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 1"]
  BulletList[18, 27] isTight
    BulletListItem[18, 27] open:[18, 19, "+"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 1"]
````````````````````````````````


Without bullet matching for items within a list

```````````````````````````````` example(List - No Bullet Match: 2) options(list-no-bullet-match)
- item 1
* item 2
+ item 3
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
</ul>
.
Document[0, 27]
  BulletList[0, 27] isTight
    BulletListItem[0, 9] open:[0, 1, "-"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "*"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 27] open:[18, 19, "+"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
````````````````````````````````


### List - No Manual Start

With start

```````````````````````````````` example List - No Manual Start: 1
2. item 1
1. item 2
3. item 3
.
<ol start="2">
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
</ol>
.
Document[0, 30]
  OrderedList[0, 30] isTight start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
    OrderedListItem[20, 30] open:[20, 22, "3."] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 3"]
````````````````````````````````


Without start

```````````````````````````````` example(List - No Manual Start: 2) options(list-no-start)
2. item 1
1. item 1
1. item 1
.
<ol>
  <li>item 1</li>
  <li>item 1</li>
  <li>item 1</li>
</ol>
.
Document[0, 30]
  OrderedList[0, 30] isTight start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 1"]
    OrderedListItem[20, 30] open:[20, 22, "1."] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
````````````````````````````````


### List - Paragraph Break Options

Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1.

```````````````````````````````` example(List - Paragraph Break Options: 1) options(bullet-no-para-break, ordered-no-para-break)
1. this is a list
1. item 1
   1. item 2
.
<ol>
  <li>this is a list</li>
  <li>item 1
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
.
Document[0, 41]
  OrderedList[0, 41] isTight delimiter:'.'
    OrderedListItem[0, 18] open:[0, 2, "1."] isTight
      Paragraph[3, 18]
        Text[3, 17] chars:[3, 17, "this  …  list"]
    OrderedListItem[18, 41] open:[18, 20, "1."] isTight
      Paragraph[21, 28]
        Text[21, 27] chars:[21, 27, "item 1"]
      OrderedList[31, 41] isTight delimiter:'.'
        OrderedListItem[31, 41] open:[31, 33, "1."] isTight
          Paragraph[34, 41]
            Text[34, 40] chars:[34, 40, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1.

```````````````````````````````` example(List - Paragraph Break Options: 2) options(bullet-no-para-break, ordered-no-para-break)
1. this is a list
1. item 1
   2. item 2
.
<ol>
  <li>this is a list</li>
  <li>item 1
  2. item 2</li>
</ol>
.
Document[0, 41]
  OrderedList[0, 41] isTight delimiter:'.'
    OrderedListItem[0, 18] open:[0, 2, "1."] isTight
      Paragraph[3, 18]
        Text[3, 17] chars:[3, 17, "this  …  list"]
    OrderedListItem[18, 41] open:[18, 20, "1."] isTight
      Paragraph[21, 41]
        Text[21, 27] chars:[21, 27, "item 1"]
        SoftLineBreak[27, 28]
        Text[31, 40] chars:[31, 40, "2. item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1 or no ordered start restriction

```````````````````````````````` example(List - Paragraph Break Options: 3) options(bullet-no-para-break, ordered-non-1-para-break)
This is a paragraph
2. this is a list
1. item 1
   2. item 2
.
<p>This is a paragraph</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
  2. item 2</li>
</ol>
.
Document[0, 61]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 61] isTight start:2 delimiter:'.'
    OrderedListItem[20, 38] open:[20, 22, "2."] isTight
      Paragraph[23, 38]
        Text[23, 37] chars:[23, 37, "this  …  list"]
    OrderedListItem[38, 61] open:[38, 40, "1."] isTight
      Paragraph[41, 61]
        Text[41, 47] chars:[41, 47, "item 1"]
        SoftLineBreak[47, 48]
        Text[51, 60] chars:[51, 60, "2. item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1 or no ordered start restriction

```````````````````````````````` example(List - Paragraph Break Options: 4) options(bullet-no-para-break, ordered-non-1-para-break, ordered-non-1-item-break)
This is a paragraph
2. this is a list
1. item 1
   2. item 2
.
<p>This is a paragraph</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
    <ol start="2">
      <li>item 2</li>
    </ol>
  </li>
</ol>
.
Document[0, 61]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 61] isTight start:2 delimiter:'.'
    OrderedListItem[20, 38] open:[20, 22, "2."] isTight
      Paragraph[23, 38]
        Text[23, 37] chars:[23, 37, "this  …  list"]
    OrderedListItem[38, 61] open:[38, 40, "1."] isTight
      Paragraph[41, 48]
        Text[41, 47] chars:[41, 47, "item 1"]
      OrderedList[51, 61] isTight start:2 delimiter:'.'
        OrderedListItem[51, 61] open:[51, 53, "2."] isTight
          Paragraph[54, 61]
            Text[54, 60] chars:[54, 60, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line and sub-lists only when
starting with 1 or no ordered start restriction

```````````````````````````````` example(List - Paragraph Break Options: 5) options(bullet-no-para-break, ordered-no-para-break, ordered-non-1-item-break)
1. this is a list
1. item 1
   2. item 2
.
<ol>
  <li>this is a list</li>
  <li>item 1
    <ol start="2">
      <li>item 2</li>
    </ol>
  </li>
</ol>
.
Document[0, 41]
  OrderedList[0, 41] isTight delimiter:'.'
    OrderedListItem[0, 18] open:[0, 2, "1."] isTight
      Paragraph[3, 18]
        Text[3, 17] chars:[3, 17, "this  …  list"]
    OrderedListItem[18, 41] open:[18, 20, "1."] isTight
      Paragraph[21, 28]
        Text[21, 27] chars:[21, 27, "item 1"]
      OrderedList[31, 41] isTight start:2 delimiter:'.'
        OrderedListItem[31, 41] open:[31, 33, "2."] isTight
          Paragraph[34, 41]
            Text[34, 40] chars:[34, 40, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line.

```````````````````````````````` example(List - Paragraph Break Options: 6) options(bullet-no-para-break, ordered-no-para-break)
- this is a list
- item 1
  - item 2
.
<ul>
  <li>this is a list</li>
  <li>item 1
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 37]
  BulletList[0, 37] isTight
    BulletListItem[0, 17] open:[0, 1, "-"] isTight
      Paragraph[2, 17]
        Text[2, 16] chars:[2, 16, "this  …  list"]
    BulletListItem[17, 37] open:[17, 18, "-"] isTight
      Paragraph[19, 26]
        Text[19, 25] chars:[19, 25, "item 1"]
      BulletList[28, 37] isTight
        BulletListItem[28, 37] open:[28, 29, "-"] isTight
          Paragraph[30, 37]
            Text[30, 36] chars:[30, 36, "item 2"]
````````````````````````````````


With relaxed start. Lists can start without preceding blank lines.

```````````````````````````````` example List - Paragraph Break Options: 7
This is a paragraph
2. item 1
1. item 2

2. this is a list
1. item 1
    1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
    - item 2
.
<p>This is a paragraph
2. item 1</p>
<ol>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph</p>
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 163]
  Paragraph[0, 30]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 29] chars:[20, 29, "2. item 1"]
  OrderedList[30, 83] isLoose delimiter:'.'
    OrderedListItem[30, 40] open:[30, 32, "1."] isLoose
      Paragraph[33, 40]
        Text[33, 39] chars:[33, 39, "item 2"]
    OrderedListItem[41, 59] open:[41, 43, "2."] isLoose
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 83] open:[59, 61, "1."] isLoose
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[73, 83] isTight delimiter:'.'
        OrderedListItem[73, 83] open:[73, 75, "1."] isTight
          Paragraph[76, 83]
            Text[76, 82] chars:[76, 82, "item 2"]
  Paragraph[84, 105]
    Text[84, 103] chars:[84, 103, "This  … graph"]
  BulletList[105, 163] isLoose
    BulletListItem[105, 114] open:[105, 106, "-"] isLoose
      Paragraph[107, 114]
        Text[107, 113] chars:[107, 113, "item 1"]
    BulletListItem[114, 123] open:[114, 115, "-"] isLoose
      Paragraph[116, 123]
        Text[116, 122] chars:[116, 122, "item 2"]
    BulletListItem[124, 141] open:[124, 125, "-"] isLoose
      Paragraph[126, 141]
        Text[126, 140] chars:[126, 140, "this  …  list"]
    BulletListItem[141, 163] open:[141, 142, "-"] isLoose
      Paragraph[143, 150]
        Text[143, 149] chars:[143, 149, "item 1"]
      BulletList[154, 163] isTight
        BulletListItem[154, 163] open:[154, 155, "-"] isTight
          Paragraph[156, 163]
            Text[156, 162] chars:[156, 162, "item 2"]
````````````````````````````````


Without relaxed start. Lists start only if preceded by a blank line. Items and sub-items can
start without a blank line.

```````````````````````````````` example(List - Paragraph Break Options: 8) options(bullet-no-para-break, ordered-no-para-break)
This is a paragraph
2. item 1
1. item 2

2. this is a list
1. item 1
   1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
  - item 2
.
<p>This is a paragraph
2. item 1
1. item 2</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph
- item 1
- item 2</p>
<ul>
  <li>this is a list</li>
  <li>item 1
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 160]
  Paragraph[0, 40]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 29] chars:[20, 29, "2. item 1"]
    SoftLineBreak[29, 30]
    Text[30, 39] chars:[30, 39, "1. item 2"]
  OrderedList[41, 82] isTight start:2 delimiter:'.'
    OrderedListItem[41, 59] open:[41, 43, "2."] isTight
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 82] open:[59, 61, "1."] isTight
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[72, 82] isTight delimiter:'.'
        OrderedListItem[72, 82] open:[72, 74, "1."] isTight
          Paragraph[75, 82]
            Text[75, 81] chars:[75, 81, "item 2"]
  Paragraph[83, 122]
    Text[83, 102] chars:[83, 102, "This  … graph"]
    SoftLineBreak[103, 104]
    Text[104, 112] chars:[104, 112, "- item 1"]
    SoftLineBreak[112, 113]
    Text[113, 121] chars:[113, 121, "- item 2"]
  BulletList[123, 160] isTight
    BulletListItem[123, 140] open:[123, 124, "-"] isTight
      Paragraph[125, 140]
        Text[125, 139] chars:[125, 139, "this  …  list"]
    BulletListItem[140, 160] open:[140, 141, "-"] isTight
      Paragraph[142, 149]
        Text[142, 148] chars:[142, 148, "item 1"]
      BulletList[151, 160] isTight
        BulletListItem[151, 160] open:[151, 152, "-"] isTight
          Paragraph[153, 160]
            Text[153, 159] chars:[153, 159, "item 2"]
````````````````````````````````


Without relaxed start for bullet Lists start only if preceded by a blank line. Items and
sub-items can start without a blank line.

```````````````````````````````` example(List - Paragraph Break Options: 9) options(bullet-no-para-break)
This is a paragraph
1. item 1
1. item 2

2. this is a list
1. item 1
   1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
  - item 2
.
<p>This is a paragraph</p>
<ol>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph
- item 1
- item 2</p>
<ul>
  <li>this is a list</li>
  <li>item 1
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 160]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 82] isLoose delimiter:'.'
    OrderedListItem[20, 30] open:[20, 22, "1."] isLoose
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    OrderedListItem[30, 40] open:[30, 32, "1."] isLoose
      Paragraph[33, 40]
        Text[33, 39] chars:[33, 39, "item 2"]
    OrderedListItem[41, 59] open:[41, 43, "2."] isLoose
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 82] open:[59, 61, "1."] isLoose
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[72, 82] isTight delimiter:'.'
        OrderedListItem[72, 82] open:[72, 74, "1."] isTight
          Paragraph[75, 82]
            Text[75, 81] chars:[75, 81, "item 2"]
  Paragraph[83, 122]
    Text[83, 102] chars:[83, 102, "This  … graph"]
    SoftLineBreak[103, 104]
    Text[104, 112] chars:[104, 112, "- item 1"]
    SoftLineBreak[112, 113]
    Text[113, 121] chars:[113, 121, "- item 2"]
  BulletList[123, 160] isTight
    BulletListItem[123, 140] open:[123, 124, "-"] isTight
      Paragraph[125, 140]
        Text[125, 139] chars:[125, 139, "this  …  list"]
    BulletListItem[140, 160] open:[140, 141, "-"] isTight
      Paragraph[142, 149]
        Text[142, 148] chars:[142, 148, "item 1"]
      BulletList[151, 160] isTight
        BulletListItem[151, 160] open:[151, 152, "-"] isTight
          Paragraph[153, 160]
            Text[153, 159] chars:[153, 159, "item 2"]
````````````````````````````````


With relaxed start but not for ordered lists. Ordered lists start only if preceded by a blank
line.

```````````````````````````````` example(List - Paragraph Break Options: 10) options(ordered-no-para-break)
This is a paragraph
2. item 1
1. item 2

2. this is a list
1. item 1
   1. item 2

This is a paragraph 
- item 1
- item 2

- this is a list
- item 1
  - item 2
.
<p>This is a paragraph
2. item 1
1. item 2</p>
<ol start="2">
  <li>this is a list</li>
  <li>item 1
    <ol>
      <li>item 2</li>
    </ol>
  </li>
</ol>
<p>This is a paragraph</p>
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>this is a list</p>
  </li>
  <li>
    <p>item 1</p>
    <ul>
      <li>item 2</li>
    </ul>
  </li>
</ul>
.
Document[0, 160]
  Paragraph[0, 40]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 29] chars:[20, 29, "2. item 1"]
    SoftLineBreak[29, 30]
    Text[30, 39] chars:[30, 39, "1. item 2"]
  OrderedList[41, 82] isTight start:2 delimiter:'.'
    OrderedListItem[41, 59] open:[41, 43, "2."] isTight
      Paragraph[44, 59]
        Text[44, 58] chars:[44, 58, "this  …  list"]
    OrderedListItem[59, 82] open:[59, 61, "1."] isTight
      Paragraph[62, 69]
        Text[62, 68] chars:[62, 68, "item 1"]
      OrderedList[72, 82] isTight delimiter:'.'
        OrderedListItem[72, 82] open:[72, 74, "1."] isTight
          Paragraph[75, 82]
            Text[75, 81] chars:[75, 81, "item 2"]
  Paragraph[83, 104]
    Text[83, 102] chars:[83, 102, "This  … graph"]
  BulletList[104, 160] isLoose
    BulletListItem[104, 113] open:[104, 105, "-"] isLoose
      Paragraph[106, 113]
        Text[106, 112] chars:[106, 112, "item 1"]
    BulletListItem[113, 122] open:[113, 114, "-"] isLoose
      Paragraph[115, 122]
        Text[115, 121] chars:[115, 121, "item 2"]
    BulletListItem[123, 140] open:[123, 124, "-"] isLoose
      Paragraph[125, 140]
        Text[125, 139] chars:[125, 139, "this  …  list"]
    BulletListItem[140, 160] open:[140, 141, "-"] isLoose
      Paragraph[142, 149]
        Text[142, 148] chars:[142, 148, "item 1"]
      BulletList[151, 160] isTight
        BulletListItem[151, 160] open:[151, 152, "-"] isTight
          Paragraph[153, 160]
            Text[153, 159] chars:[153, 159, "item 2"]
````````````````````````````````


Ordered items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 11) options(ordered-no-para-break, ordered-no-item-break)
This is a paragraph
2. not item 1
1. not item 2
.
<p>This is a paragraph
2. not item 1
1. not item 2</p>
.
Document[0, 48]
  Paragraph[0, 48]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 33] chars:[20, 33, "2. no … tem 1"]
    SoftLineBreak[33, 34]
    Text[34, 47] chars:[34, 47, "1. no … tem 2"]
````````````````````````````````


Ordered items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 12) options(ordered-no-para-break, ordered-no-item-break)
1. item 1.0

2. item 2.0
1. not item 2

1. item 3.0
.
<ol>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    1. not item 2</p>
  </li>
  <li>
    <p>item 3.0</p>
  </li>
</ol>
.
Document[0, 52]
  OrderedList[0, 52] isLoose delimiter:'.'
    OrderedListItem[0, 12] open:[0, 2, "1."] isLoose
      Paragraph[3, 12]
        Text[3, 11] chars:[3, 11, "item 1.0"]
    OrderedListItem[13, 39] open:[13, 15, "2."] isLoose
      Paragraph[16, 39]
        Text[16, 24] chars:[16, 24, "item 2.0"]
        SoftLineBreak[24, 25]
        Text[25, 38] chars:[25, 38, "1. no … tem 2"]
    OrderedListItem[40, 52] open:[40, 42, "1."] isLoose
      Paragraph[43, 52]
        Text[43, 51] chars:[43, 51, "item 3.0"]
````````````````````````````````


Ordered items must have a blank line before them, but not bullet items

```````````````````````````````` example(List - Paragraph Break Options: 13) options(ordered-no-para-break, ordered-no-item-break)
This is a paragraph
- item 1
- item 2
.
<p>This is a paragraph</p>
<ul>
  <li>item 1</li>
  <li>item 2</li>
</ul>
.
Document[0, 38]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  BulletList[20, 38] isTight
    BulletListItem[20, 29] open:[20, 21, "-"] isTight
      Paragraph[22, 29]
        Text[22, 28] chars:[22, 28, "item 1"]
    BulletListItem[29, 38] open:[29, 30, "-"] isTight
      Paragraph[31, 38]
        Text[31, 37] chars:[31, 37, "item 2"]
````````````````````````````````


Bullet items must have a blank line before them when preceded by paragraph but should not append
following child paragraph

```````````````````````````````` example(List - Paragraph Break Options: 14) options(bullet-no-para-break, list-fixed-indent, list-no-type-match, list-no-loose)
- item 1 paragraph
    * sublist
- item 2 paragraph

    paragraph
.
<ul>
  <li>item 1 paragraph
    <ul>
      <li>sublist</li>
    </ul>
  </li>
  <li>item 2 paragraph
  <p>paragraph</p>
  </li>
</ul>
.
Document[0, 67]
  BulletList[0, 67] isTight
    BulletListItem[0, 33] open:[0, 1, "-"] isTight
      Paragraph[2, 19]
        Text[2, 18] chars:[2, 18, "item  … graph"]
      BulletList[23, 33] isTight
        BulletListItem[23, 33] open:[23, 24, "*"] isTight
          Paragraph[25, 33]
            Text[25, 32] chars:[25, 32, "sublist"]
    BulletListItem[33, 67] open:[33, 34, "-"] isTight
      Paragraph[35, 52]
        Text[35, 51] chars:[35, 51, "item  … graph"]
      Paragraph[57, 67]
        Text[57, 66] chars:[57, 66, "paragraph"]
````````````````````````````````


Bullet items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 15) options(bullet-no-para-break, bullet-no-item-break)
This is a paragraph
- not item 1
- not item 2
.
<p>This is a paragraph
- not item 1
- not item 2</p>
.
Document[0, 46]
  Paragraph[0, 46]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 32] chars:[20, 32, "- not … tem 1"]
    SoftLineBreak[32, 33]
    Text[33, 45] chars:[33, 45, "- not … tem 2"]
````````````````````````````````


Bullet items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 16) options(bullet-no-para-break, bullet-no-item-break)
- item 1.0

- item 2.0
- not item 2

- item 3.0
.
<ul>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    - not item 2</p>
  </li>
  <li>
    <p>item 3.0</p>
  </li>
</ul>
.
Document[0, 48]
  BulletList[0, 48] isLoose
    BulletListItem[0, 11] open:[0, 1, "-"] isLoose
      Paragraph[2, 11]
        Text[2, 10] chars:[2, 10, "item 1.0"]
    BulletListItem[12, 36] open:[12, 13, "-"] isLoose
      Paragraph[14, 36]
        Text[14, 22] chars:[14, 22, "item 2.0"]
        SoftLineBreak[22, 23]
        Text[23, 35] chars:[23, 35, "- not … tem 2"]
    BulletListItem[37, 48] open:[37, 38, "-"] isLoose
      Paragraph[39, 48]
        Text[39, 47] chars:[39, 47, "item 3.0"]
````````````````````````````````


Bullet items must have a blank line before them, but not ordered items

```````````````````````````````` example(List - Paragraph Break Options: 17) options(bullet-no-para-break, bullet-no-item-break)
This is a paragraph
1. item 1
2. item 2
.
<p>This is a paragraph</p>
<ol>
  <li>item 1</li>
  <li>item 2</li>
</ol>
.
Document[0, 40]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  OrderedList[20, 40] isTight delimiter:'.'
    OrderedListItem[20, 30] open:[20, 22, "1."] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    OrderedListItem[30, 40] open:[30, 32, "2."] isTight
      Paragraph[33, 40]
        Text[33, 39] chars:[33, 39, "item 2"]
````````````````````````````````


All items must have a blank line before them

```````````````````````````````` example(List - Paragraph Break Options: 18) options(bullet-no-para-break, bullet-no-item-break, ordered-no-para-break, ordered-no-item-break)
This is a paragraph
2. not item 1
1. not item 2

1. item 1.0

2. item 2.0
1. not item 2

1. item 2

This is a paragraph 
- not item 1
- not item 2

- item 1.0

- item 2.0
- not item 1
- not item 2

- item 3.0
.
<p>This is a paragraph
2. not item 1
1. not item 2</p>
<ol>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    1. not item 2</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
</ol>
<p>This is a paragraph
- not item 1
- not item 2</p>
<ul>
  <li>
    <p>item 1.0</p>
  </li>
  <li>
    <p>item 2.0
    - not item 1
    - not item 2</p>
  </li>
  <li>
    <p>item 3.0</p>
  </li>
</ul>
.
Document[0, 209]
  Paragraph[0, 48]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 33] chars:[20, 33, "2. no … tem 1"]
    SoftLineBreak[33, 34]
    Text[34, 47] chars:[34, 47, "1. no … tem 2"]
  OrderedList[49, 99] isLoose delimiter:'.'
    OrderedListItem[49, 61] open:[49, 51, "1."] isLoose
      Paragraph[52, 61]
        Text[52, 60] chars:[52, 60, "item 1.0"]
    OrderedListItem[62, 88] open:[62, 64, "2."] isLoose
      Paragraph[65, 88]
        Text[65, 73] chars:[65, 73, "item 2.0"]
        SoftLineBreak[73, 74]
        Text[74, 87] chars:[74, 87, "1. no … tem 2"]
    OrderedListItem[89, 99] open:[89, 91, "1."] isLoose
      Paragraph[92, 99]
        Text[92, 98] chars:[92, 98, "item 2"]
  Paragraph[100, 147]
    Text[100, 119] chars:[100, 119, "This  … graph"]
    SoftLineBreak[120, 121]
    Text[121, 133] chars:[121, 133, "- not … tem 1"]
    SoftLineBreak[133, 134]
    Text[134, 146] chars:[134, 146, "- not … tem 2"]
  BulletList[148, 209] isLoose
    BulletListItem[148, 159] open:[148, 149, "-"] isLoose
      Paragraph[150, 159]
        Text[150, 158] chars:[150, 158, "item 1.0"]
    BulletListItem[160, 197] open:[160, 161, "-"] isLoose
      Paragraph[162, 197]
        Text[162, 170] chars:[162, 170, "item 2.0"]
        SoftLineBreak[170, 171]
        Text[171, 183] chars:[171, 183, "- not … tem 1"]
        SoftLineBreak[183, 184]
        Text[184, 196] chars:[184, 196, "- not … tem 2"]
    BulletListItem[198, 209] open:[198, 199, "-"] isLoose
      Paragraph[200, 209]
        Text[200, 208] chars:[200, 208, "item 3.0"]
````````````````````````````````


### List - Marker Options

Without ordered items dot only

```````````````````````````````` example List - Marker Options: 1
1. item 1
2. item 2

1) item b

2) item c

.
<ol>
  <li>item 1</li>
  <li>item 2</li>
</ol>
<ol>
  <li>
    <p>item b</p>
  </li>
  <li>
    <p>item c</p>
  </li>
</ol>
.
Document[0, 43]
  OrderedList[0, 20] isTight delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "1."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "2."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
  OrderedList[21, 42] isLoose delimiter:')'
    OrderedListItem[21, 31] open:[21, 23, "1)"] isLoose
      Paragraph[24, 31]
        Text[24, 30] chars:[24, 30, "item b"]
    OrderedListItem[32, 42] open:[32, 34, "2)"] isLoose
      Paragraph[35, 42]
        Text[35, 41] chars:[35, 41, "item c"]
````````````````````````````````


With ordered items dot only

```````````````````````````````` example(List - Marker Options: 2) options(ordered-dot-only)
1. item 1
2. item 2

1) item b

2) item c

.
<ol>
  <li>item 1</li>
  <li>item 2</li>
</ol>
<p>1) item b</p>
<p>2) item c</p>
.
Document[0, 43]
  OrderedList[0, 20] isTight delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "1."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "2."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
  Paragraph[21, 31]
    Text[21, 30] chars:[21, 30, "1) item b"]
  Paragraph[32, 42]
    Text[32, 41] chars:[32, 41, "2) item c"]
````````````````````````````````


An ordered list after bullet list with no bullet matching

```````````````````````````````` example(List - Marker Options: 3) options(list-no-bullet-match)
- item 1
- item 2
+ item 3
* item 4

2. item 1
1. item 2
.
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4</li>
</ul>
<ol start="2">
  <li>item 1</li>
  <li>item 2</li>
</ol>
.
Document[0, 57]
  BulletList[0, 36] isTight
    BulletListItem[0, 9] open:[0, 1, "-"] isTight
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "-"] isTight
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    BulletListItem[18, 27] open:[18, 19, "+"] isTight
      Paragraph[20, 27]
        Text[20, 26] chars:[20, 26, "item 3"]
    BulletListItem[27, 36] open:[27, 28, "*"] isTight
      Paragraph[29, 36]
        Text[29, 35] chars:[29, 35, "item 4"]
  OrderedList[37, 57] isTight start:2 delimiter:'.'
    OrderedListItem[37, 47] open:[37, 39, "2."] isTight
      Paragraph[40, 47]
        Text[40, 46] chars:[40, 46, "item 1"]
    OrderedListItem[47, 57] open:[47, 49, "1."] isTight
      Paragraph[50, 57]
        Text[50, 56] chars:[50, 56, "item 2"]
````````````````````````````````


A bullet list after an ordered list with no bullet matching

```````````````````````````````` example(List - Marker Options: 4) options(list-no-bullet-match)
2. item 1
1. item 2

- item 1
- item 2
+ item 3
* item 4
.
<ol start="2">
  <li>item 1</li>
  <li>item 2</li>
</ol>
<ul>
  <li>item 1</li>
  <li>item 2</li>
  <li>item 3</li>
  <li>item 4</li>
</ul>
.
Document[0, 57]
  OrderedList[0, 20] isTight start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
  BulletList[21, 57] isTight
    BulletListItem[21, 30] open:[21, 22, "-"] isTight
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    BulletListItem[30, 39] open:[30, 31, "-"] isTight
      Paragraph[32, 39]
        Text[32, 38] chars:[32, 38, "item 2"]
    BulletListItem[39, 48] open:[39, 40, "+"] isTight
      Paragraph[41, 48]
        Text[41, 47] chars:[41, 47, "item 3"]
    BulletListItem[48, 57] open:[48, 49, "*"] isTight
      Paragraph[50, 57]
        Text[50, 56] chars:[50, 56, "item 4"]
````````````````````````````````


An ordered list after bullet list, no type match

```````````````````````````````` example(List - Marker Options: 5) options(list-no-type-match)
- item 1
- item 2

2. item 1
1. item 2
.
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
</ul>
.
Document[0, 39]
  BulletList[0, 39] isLoose
    BulletListItem[0, 9] open:[0, 1, "-"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[9, 18] open:[9, 10, "-"] isLoose
      Paragraph[11, 18]
        Text[11, 17] chars:[11, 17, "item 2"]
    OrderedListItem[19, 29] open:[19, 21, "2."] isLoose
      Paragraph[22, 29]
        Text[22, 28] chars:[22, 28, "item 1"]
    OrderedListItem[29, 39] open:[29, 31, "1."] isLoose
      Paragraph[32, 39]
        Text[32, 38] chars:[32, 38, "item 2"]
````````````````````````````````


A bullet list after an ordered list, no type match

```````````````````````````````` example(List - Marker Options: 6) options(list-no-type-match)
2. item 1
1. item 2

- item 1
- item 2
.
<ol start="2">
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
  </li>
</ol>
.
Document[0, 39]
  OrderedList[0, 39] isLoose start:2 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "2."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 20] open:[10, 12, "1."] isLoose
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
    BulletListItem[21, 30] open:[21, 22, "-"] isLoose
      Paragraph[23, 30]
        Text[23, 29] chars:[23, 29, "item 1"]
    BulletListItem[30, 39] open:[30, 31, "-"] isLoose
      Paragraph[32, 39]
        Text[32, 38] chars:[32, 38, "item 2"]
````````````````````````````````


An ordered list item can interrupt a previous list item's paragraph

```````````````````````````````` example List - Marker Options: 7
1. item 1
lazy continuation
2. item 2
.
<ol>
  <li>item 1
  lazy continuation</li>
  <li>item 2</li>
</ol>
.
Document[0, 38]
  OrderedList[0, 38] isTight delimiter:'.'
    OrderedListItem[0, 28] open:[0, 2, "1."] isTight
      Paragraph[3, 28]
        Text[3, 9] chars:[3, 9, "item 1"]
        SoftLineBreak[9, 10]
        Text[10, 27] chars:[10, 27, "lazy  … ation"]
    OrderedListItem[28, 38] open:[28, 30, "2."] isTight
      Paragraph[31, 38]
        Text[31, 37] chars:[31, 37, "item 2"]
````````````````````````````````


An ordered list sub item can interrupt its parent item's paragraph even if it does not start
with 1 when start setting is disabled.

```````````````````````````````` example(List - Marker Options: 8) options(list-no-start)
1. item 1
lazy continuation
   2. item 1.1
.
<ol>
  <li>item 1
  lazy continuation
    <ol>
      <li>item 1.1</li>
    </ol>
  </li>
</ol>
.
Document[0, 43]
  OrderedList[0, 43] isTight delimiter:'.'
    OrderedListItem[0, 43] open:[0, 2, "1."] isTight
      Paragraph[3, 28]
        Text[3, 9] chars:[3, 9, "item 1"]
        SoftLineBreak[9, 10]
        Text[10, 27] chars:[10, 27, "lazy  … ation"]
      OrderedList[31, 43] isTight start:2 delimiter:'.'
        OrderedListItem[31, 43] open:[31, 33, "2."] isTight
          Paragraph[34, 43]
            Text[34, 42] chars:[34, 42, "item 1.1"]
````````````````````````````````


nested

```````````````````````````````` example(List - Marker Options: 9) options(list-fixed-indent, list-no-start)
4. item 1
3. item 2
    2. item 2.1
1. item 3
.
<ol>
  <li>item 1</li>
  <li>item 2
    <ol>
      <li>item 2.1</li>
    </ol>
  </li>
  <li>item 3</li>
</ol>
.
Document[0, 46]
  OrderedList[0, 46] isTight start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isTight
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 36] open:[10, 12, "3."] isTight
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
      OrderedList[24, 36] isTight start:2 delimiter:'.'
        OrderedListItem[24, 36] open:[24, 26, "2."] isTight
          Paragraph[27, 36]
            Text[27, 35] chars:[27, 35, "item 2.1"]
    OrderedListItem[36, 46] open:[36, 38, "1."] isTight
      Paragraph[39, 46]
        Text[39, 45] chars:[39, 45, "item 3"]
````````````````````````````````


nested, no ordered start, no ordered para break, no ordered item paragraph break

```````````````````````````````` example(List - Marker Options: 10) options(list-fixed-indent, list-no-start, ordered-no-para-break, ordered-no-item-break)
4. item 1
3. item 2
    2. item 2.1
1. item 3
.
<ol>
  <li>item 1
  3. item 2
  2. item 2.1
  1. item 3</li>
</ol>
.
Document[0, 46]
  OrderedList[0, 46] isTight start:4 delimiter:'.'
    OrderedListItem[0, 46] open:[0, 2, "4."] isTight
      Paragraph[3, 46]
        Text[3, 9] chars:[3, 9, "item 1"]
        SoftLineBreak[9, 10]
        Text[10, 19] chars:[10, 19, "3. item 2"]
        SoftLineBreak[19, 20]
        Text[24, 35] chars:[24, 35, "2. it … m 2.1"]
        SoftLineBreak[35, 36]
        Text[36, 45] chars:[36, 45, "1. item 3"]
````````````````````````````````


nested, no ordered start, no ordered para break, no ordered item paragraph break

```````````````````````````````` example(List - Marker Options: 11) options(list-fixed-indent, list-no-start, ordered-no-para-break, ordered-no-item-break)
4. item 1

3. item 2

    2. item 2.1
    
1. item 3
.
<ol>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ol>
      <li>item 2.1</li>
    </ol>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ol>
.
Document[0, 53]
  OrderedList[0, 53] isLoose start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[11, 38] open:[11, 13, "3."] isLoose
      Paragraph[14, 21]
        Text[14, 20] chars:[14, 20, "item 2"]
      OrderedList[26, 38] isTight start:2 delimiter:'.'
        OrderedListItem[26, 38] open:[26, 28, "2."] isTight
          Paragraph[29, 38]
            Text[29, 37] chars:[29, 37, "item 2.1"]
    OrderedListItem[43, 53] open:[43, 45, "1."] isLoose
      Paragraph[46, 53]
        Text[46, 52] chars:[46, 52, "item 3"]
````````````````````````````````


no relaxed ordered start with exception for another item's paragraph

```````````````````````````````` example(List - Marker Options: 12) options(list-fixed-indent, list-no-start, ordered-no-para-break)
4. item 1
3. item 2
    2. item 2.1
    
    paragraph 
    1. with lazy continuation looking like an item
1. item 3
.
<ol>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ol>
      <li>item 2.1</li>
    </ol>
    <p>paragraph
    1. with lazy continuation looking like an item</p>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ol>
.
Document[0, 117]
  OrderedList[0, 117] isLoose start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 107] open:[10, 12, "3."] isLoose
      Paragraph[13, 20]
        Text[13, 19] chars:[13, 19, "item 2"]
      OrderedList[24, 36] isTight start:2 delimiter:'.'
        OrderedListItem[24, 36] open:[24, 26, "2."] isTight
          Paragraph[27, 36]
            Text[27, 35] chars:[27, 35, "item 2.1"]
      Paragraph[45, 107]
        Text[45, 54] chars:[45, 54, "paragraph"]
        SoftLineBreak[55, 56]
        Text[60, 106] chars:[60, 106, "1. wi …  item"]
    OrderedListItem[107, 117] open:[107, 109, "1."] isLoose
      Paragraph[110, 117]
        Text[110, 116] chars:[110, 116, "item 3"]
````````````````````````````````


no relaxed ordered start with exception for another item's paragraph but only if manual list
start is enabled

```````````````````````````````` example(List - Marker Options: 13) options(list-fixed-indent, ordered-no-para-break)
4. item 1
3. item 2
    2. item 2.1
    
    paragraph 
    1. with lazy continuation looking like an item
1. item 3
.
<ol start="4">
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2
    2. item 2.1</p>
    <p>paragraph
    1. with lazy continuation looking like an item</p>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ol>
.
Document[0, 117]
  OrderedList[0, 117] isLoose start:4 delimiter:'.'
    OrderedListItem[0, 10] open:[0, 2, "4."] isLoose
      Paragraph[3, 10]
        Text[3, 9] chars:[3, 9, "item 1"]
    OrderedListItem[10, 107] open:[10, 12, "3."] isLoose
      Paragraph[13, 36]
        Text[13, 19] chars:[13, 19, "item 2"]
        SoftLineBreak[19, 20]
        Text[24, 35] chars:[24, 35, "2. it … m 2.1"]
      Paragraph[45, 107]
        Text[45, 54] chars:[45, 54, "paragraph"]
        SoftLineBreak[55, 56]
        Text[60, 106] chars:[60, 106, "1. wi …  item"]
    OrderedListItem[107, 117] open:[107, 109, "1."] isLoose
      Paragraph[110, 117]
        Text[110, 116] chars:[110, 116, "item 3"]
````````````````````````````````


nested, no bullet para break, no bullet item paragraph break

```````````````````````````````` example(List - Marker Options: 14) options(list-fixed-indent, bullet-no-para-break, bullet-no-item-break)
- item 1
- item 2
    - item 2.1
- item 3
.
<ul>
  <li>item 1
  - item 2
  - item 2.1
  - item 3</li>
</ul>
.
Document[0, 42]
  BulletList[0, 42] isTight
    BulletListItem[0, 42] open:[0, 1, "-"] isTight
      Paragraph[2, 42]
        Text[2, 8] chars:[2, 8, "item 1"]
        SoftLineBreak[8, 9]
        Text[9, 17] chars:[9, 17, "- item 2"]
        SoftLineBreak[17, 18]
        Text[22, 32] chars:[22, 32, "- item 2.1"]
        SoftLineBreak[32, 33]
        Text[33, 41] chars:[33, 41, "- item 3"]
````````````````````````````````


nested, no ordered start, no ordered para break, no ordered item paragraph break

```````````````````````````````` example(List - Marker Options: 15) options(list-fixed-indent, bullet-no-para-break, bullet-no-item-break)
- item 1

- item 2

    - item 2.1
    
- item 3
.
<ul>
  <li>
    <p>item 1</p>
  </li>
  <li>
    <p>item 2</p>
    <ul>
      <li>item 2.1</li>
    </ul>
  </li>
  <li>
    <p>item 3</p>
  </li>
</ul>
.
Document[0, 49]
  BulletList[0, 49] isLoose
    BulletListItem[0, 9] open:[0, 1, "-"] isLoose
      Paragraph[2, 9]
        Text[2, 8] chars:[2, 8, "item 1"]
    BulletListItem[10, 35] open:[10, 11, "-"] isLoose
      Paragraph[12, 19]
        Text[12, 18] chars:[12, 18, "item 2"]
      BulletList[24, 35] isTight
        BulletListItem[24, 35] open:[24, 25, "-"] isTight
          Paragraph[26, 35]
            Text[26, 34] chars:[26, 34, "item 2.1"]
    BulletListItem[40, 49] open:[40, 41, "-"] isLoose
      Paragraph[42, 49]
        Text[42, 48] chars:[42, 48, "item 3"]
````````````````````````````````


mismatched item to sub-item

```````````````````````````````` example(List - Marker Options: 16) options(list-item-mismatch-to-subitem)
- item
1. sub-item
1. sub-item
- item
1. sub-item
1. sub-item
.
<ul>
  <li>item
    <ol>
      <li>sub-item</li>
      <li>sub-item</li>
    </ol>
  </li>
  <li>item
    <ol>
      <li>sub-item</li>
      <li>sub-item</li>
    </ol>
  </li>
</ul>
.
Document[0, 62]
  BulletList[0, 62] isTight
    BulletListItem[0, 31] open:[0, 1, "-"] isTight
      Paragraph[2, 7]
        Text[2, 6] chars:[2, 6, "item"]
      OrderedList[7, 31] isTight delimiter:'.'
        OrderedListItem[7, 19] open:[7, 9, "1."] isTight
          Paragraph[10, 19]
            Text[10, 18] chars:[10, 18, "sub-item"]
        OrderedListItem[19, 31] open:[19, 21, "1."] isTight
          Paragraph[22, 31]
            Text[22, 30] chars:[22, 30, "sub-item"]
    BulletListItem[31, 62] open:[31, 32, "-"] isTight
      Paragraph[33, 38]
        Text[33, 37] chars:[33, 37, "item"]
      OrderedList[38, 62] isTight delimiter:'.'
        OrderedListItem[38, 50] open:[38, 40, "1."] isTight
          Paragraph[41, 50]
            Text[41, 49] chars:[41, 49, "sub-item"]
        OrderedListItem[50, 62] open:[50, 52, "1."] isTight
          Paragraph[53, 62]
            Text[53, 61] chars:[53, 61, "sub-item"]
````````````````````````````````


mismatched item to sub-item

```````````````````````````````` example(List - Marker Options: 17) options(list-item-mismatch-to-subitem)
1. item
- sub-item
- sub-item
1. item
- sub-item
- sub-item
.
<ol>
  <li>item
    <ul>
      <li>sub-item</li>
      <li>sub-item</li>
    </ul>
  </li>
  <li>item
    <ul>
      <li>sub-item</li>
      <li>sub-item</li>
    </ul>
  </li>
</ol>
.
Document[0, 60]
  OrderedList[0, 60] isTight delimiter:'.'
    OrderedListItem[0, 30] open:[0, 2, "1."] isTight
      Paragraph[3, 8]
        Text[3, 7] chars:[3, 7, "item"]
      BulletList[8, 30] isTight
        BulletListItem[8, 19] open:[8, 9, "-"] isTight
          Paragraph[10, 19]
            Text[10, 18] chars:[10, 18, "sub-item"]
        BulletListItem[19, 30] open:[19, 20, "-"] isTight
          Paragraph[21, 30]
            Text[21, 29] chars:[21, 29, "sub-item"]
    OrderedListItem[30, 60] open:[30, 32, "1."] isTight
      Paragraph[33, 38]
        Text[33, 37] chars:[33, 37, "item"]
      BulletList[38, 60] isTight
        BulletListItem[38, 49] open:[38, 39, "-"] isTight
          Paragraph[40, 49]
            Text[40, 48] chars:[40, 48, "sub-item"]
        BulletListItem[49, 60] open:[49, 50, "-"] isTight
          Paragraph[51, 60]
            Text[51, 59] chars:[51, 59, "sub-item"]
````````````````````````````````


### Thematic Break - No Relaxed Rules

With relaxed rules. Thematic break can occur without a preceding blank line. Applies to
non-dashed thematic break, dashes are a heading.

```````````````````````````````` example Thematic Break - No Relaxed Rules: 1
This is a paragraph
***
.
<p>This is a paragraph</p>
<hr />
.
Document[0, 24]
  Paragraph[0, 20]
    Text[0, 19] chars:[0, 19, "This  … graph"]
  ThematicBreak[20, 23]
````````````````````````````````


Without relaxed rules. Thematic break must be preceded by a blank line. Applies to non-dashed
thematic break, dashes are a heading.

```````````````````````````````` example(Thematic Break - No Relaxed Rules: 2) options(thematic-break-no-relaxed-start)
This is a paragraph
***
.
<p>This is a paragraph
***</p>
.
Document[0, 24]
  Paragraph[0, 24]
    Text[0, 19] chars:[0, 19, "This  … graph"]
    SoftLineBreak[19, 20]
    Text[20, 23] chars:[20, 23, "***"]
````````````````````````````````


### HTML Options

#### HTML Encode Options

Default pass it all through

```````````````````````````````` example HTML Encode Options: 1
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode all html

```````````````````````````````` example(HTML Encode Options: 2) options(escape-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
&lt;div&gt;
&lt;p&gt;paragraph&lt;/p&gt;
&lt;/div&gt;
&lt;!-- html comment block --&gt;
&lt;p&gt;paragraph&lt;/p&gt;
<p>This is a paragraph with html &lt;span style=&quot;color:red;&quot;&gt;Test&lt;/span&gt; and an html comment &lt;!-- comment --&gt; embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode html blocks

```````````````````````````````` example(HTML Encode Options: 3) options(escape-html-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
&lt;div&gt;
&lt;p&gt;paragraph&lt;/p&gt;
&lt;/div&gt;
&lt;!-- html comment block --&gt;
&lt;p&gt;paragraph&lt;/p&gt;
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode html block comments

```````````````````````````````` example(HTML Encode Options: 4) options(escape-html-comment-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
&lt;!-- html comment block --&gt;
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode inline html

```````````````````````````````` example(HTML Encode Options: 5) options(escape-inline-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html &lt;span style=&quot;color:red;&quot;&gt;Test&lt;/span&gt; and an html comment &lt;!-- comment --&gt; embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Encode inline html comments

```````````````````````````````` example(HTML Encode Options: 6) options(escape-inline-html-comments)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment &lt;!-- comment --&gt; embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


#### HTML Suppress Options

Suppress all html

```````````````````````````````` example(HTML Suppress Options: 1) options(suppress-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<p>This is a paragraph with html Test and an html comment  embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress html blocks

```````````````````````````````` example(HTML Suppress Options: 2) options(suppress-html-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress html comment blocks

```````````````````````````````` example(HTML Suppress Options: 3) options(suppress-html-comment-blocks)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress inline html

```````````````````````````````` example(HTML Suppress Options: 4) options(suppress-inline-html)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html Test and an html comment  embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


Suppress inline html comments

```````````````````````````````` example(HTML Suppress Options: 5) options(suppress-inline-html-comments)
<div>
<p>paragraph</p>
</div>

<!-- html comment block -->
<p>paragraph</p>

This is a paragraph with html <span style="color:red;">Test</span> and an html comment <!-- comment --> embedded in it.
.
<div>
<p>paragraph</p>
</div>
<!-- html comment block -->
<p>paragraph</p>
<p>This is a paragraph with html <span style="color:red;">Test</span> and an html comment  embedded in it.</p>
.
Document[0, 197]
  HtmlBlock[0, 30]
  HtmlCommentBlock[31, 59]
  HtmlBlock[59, 76]
  Paragraph[77, 197]
    Text[77, 107] chars:[77, 107, "This  … html "]
    HtmlInline[107, 132] chars:[107, 132, "<span … ed;\">"]
    Text[132, 136] chars:[132, 136, "Test"]
    HtmlInline[136, 143] chars:[136, 143, "</span>"]
    Text[143, 164] chars:[143, 164, " and  … ment "]
    HtmlInlineComment[164, 180] chars:[164, 180, "<!--  … t -->"]
    Text[180, 196] chars:[180, 196, " embe … n it."]
````````````````````````````````


## HTML Parse Inner Comments

Html comments in block

```````````````````````````````` example HTML Parse Inner Comments: 1
<!-- HTML Comment -->
<div>
</div>
.
<!-- HTML Comment -->
<div>
</div>
.
Document[0, 35]
  HtmlCommentBlock[0, 22]
  HtmlBlock[22, 35]
````````````````````````````````


Html comments in block

```````````````````````````````` example HTML Parse Inner Comments: 2
<div>
    <!-- HTML Comment -->
</div>
.
<div>
    <!-- HTML Comment -->
</div>
.
Document[0, 39]
  HtmlBlock[0, 39]
````````````````````````````````


Html comments in block

```````````````````````````````` example HTML Parse Inner Comments: 3
<div>
</div>
<!-- HTML Comment -->
.
<div>
</div>
<!-- HTML Comment -->
.
Document[0, 35]
  HtmlBlock[0, 35]
````````````````````````````````


Html comments in block, parse inner comments

```````````````````````````````` example(HTML Parse Inner Comments: 4) options(parse-inner-comments)
<!-- HTML Comment -->
<div>
</div>
.
<!-- HTML Comment -->
<div>
</div>
.
Document[0, 35]
  HtmlCommentBlock[0, 22]
  HtmlBlock[22, 35]
````````````````````````````````


Html comments in block, parse inner comments

```````````````````````````````` example(HTML Parse Inner Comments: 5) options(parse-inner-comments)
<div>
    <!-- HTML Comment -->
</div>
.
.
Document[0, 39]
  HtmlBlock[0, 39]
    HtmlInnerBlock[0, 10] chars:[0, 10, "<div>\n    "]
    HtmlInnerBlockComment[10, 31] chars:[10, 31, "<!--  … t -->"]
    HtmlInnerBlock[31, 38] chars:[31, 38, "\n</div>"]
````````````````````````````````


Html comments in block, parse inner comments

```````````````````````````````` example(HTML Parse Inner Comments: 6) options(parse-inner-comments)
<div>
</div>
<!-- HTML Comment -->
.
.
Document[0, 35]
  HtmlBlock[0, 35]
    HtmlInnerBlock[0, 13] chars:[0, 13, "<div> … div>\n"]
    HtmlInnerBlockComment[13, 34] chars:[13, 34, "<!--  … t -->"]
````````````````````````````````


## Inline HTML

kbd tag

```````````````````````````````` example Inline HTML: 1
text with <kbd>ENTER</kbd> embedded
.
<p>text with <kbd>ENTER</kbd> embedded</p>
.
Document[0, 36]
  Paragraph[0, 36]
    Text[0, 10] chars:[0, 10, "text with "]
    HtmlInline[10, 15] chars:[10, 15, "<kbd>"]
    Text[15, 20] chars:[15, 20, "ENTER"]
    HtmlInline[20, 26] chars:[20, 26, "</kbd>"]
    Text[26, 35] chars:[26, 35, " embedded"]
````````````````````````````````


## GFM compatibility

### GFM Emphasis

Emphasis around inline code spans

```````````````````````````````` example GFM Emphasis: 1
please add  `add_gtest(`**`your_unittest`**` `**`your_unittest_unittest.cc`**` )`
.
<p>please add  <code>add_gtest(</code><strong><code>your_unittest</code></strong><code></code><strong><code>your_unittest_unittest.cc</code></strong><code>)</code></p>
.
Document[0, 82]
  Paragraph[0, 82]
    Text[0, 12] chars:[0, 12, "pleas … add  "]
    Code[12, 24] textOpen:[12, 13, "`"] text:[13, 23, "add_gtest("] textClose:[23, 24, "`"]
    StrongEmphasis[24, 43] textOpen:[24, 26, "**"] text:[26, 41, "`your_unittest`"] textClose:[41, 43, "**"]
      Code[26, 41] textOpen:[26, 27, "`"] text:[27, 40, "your_ … unittest"] textClose:[40, 41, "`"]
    Code[43, 46] textOpen:[43, 44, "`"] text:[44, 45, " "] textClose:[45, 46, "`"]
    StrongEmphasis[46, 77] textOpen:[46, 48, "**"] text:[48, 75, "`your_unittest_unittest.cc`"] textClose:[75, 77, "**"]
      Code[48, 75] textOpen:[48, 49, "`"] text:[49, 74, "your_ … unittest_unittest.cc"] textClose:[74, 75, "`"]
    Code[77, 81] textOpen:[77, 78, "`"] text:[78, 80, " )"] textClose:[80, 81, "`"]
````````````````````````````````


Some weird commonmark processing of emphasis

```````````````````````````````` example GFM Emphasis: 2
**bold*bold-italic*bold**
.
<p><strong>bold<em>bold-italic</em>bold</strong></p>
.
Document[0, 26]
  Paragraph[0, 26]
    StrongEmphasis[0, 25] textOpen:[0, 2, "**"] text:[2, 23, "bold*bold-italic*bold"] textClose:[23, 25, "**"]
      Text[2, 6] chars:[2, 6, "bold"]
      Emphasis[6, 19] textOpen:[6, 7, "*"] text:[7, 18, "bold-italic"] textClose:[18, 19, "*"]
        Text[7, 18] chars:[7, 18, "bold- … talic"]
      Text[19, 23] chars:[19, 23, "bold"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 3
*a**b**c*
.
<p><em>a<strong>b</strong>c</em></p>
.
Document[0, 10]
  Paragraph[0, 10]
    Emphasis[0, 9] textOpen:[0, 1, "*"] text:[1, 8, "a**b**c"] textClose:[8, 9, "*"]
      Text[1, 2] chars:[1, 2, "a"]
      StrongEmphasis[2, 7] textOpen:[2, 4, "**"] text:[4, 5, "b"] textClose:[5, 7, "**"]
        Text[4, 5] chars:[4, 5, "b"]
      Text[7, 8] chars:[7, 8, "c"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 4
***a**b*
.
<p><em><strong>a</strong>b</em></p>
.
Document[0, 9]
  Paragraph[0, 9]
    Emphasis[0, 8] textOpen:[0, 1, "*"] text:[1, 7, "**a**b"] textClose:[7, 8, "*"]
      StrongEmphasis[1, 6] textOpen:[1, 3, "**"] text:[3, 4, "a"] textClose:[4, 6, "**"]
        Text[3, 4] chars:[3, 4, "a"]
      Text[6, 7] chars:[6, 7, "b"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 5
*b**a***

.
<p><em>b<strong>a</strong></em></p>
.
Document[0, 10]
  Paragraph[0, 9]
    Emphasis[0, 8] textOpen:[0, 1, "*"] text:[1, 7, "b**a**"] textClose:[7, 8, "*"]
      Text[1, 2] chars:[1, 2, "b"]
      StrongEmphasis[2, 7] textOpen:[2, 4, "**"] text:[4, 5, "a"] textClose:[5, 7, "**"]
        Text[4, 5] chars:[4, 5, "a"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 6
*a**b**c*

.
<p><em>a<strong>b</strong>c</em></p>
.
Document[0, 11]
  Paragraph[0, 10]
    Emphasis[0, 9] textOpen:[0, 1, "*"] text:[1, 8, "a**b**c"] textClose:[8, 9, "*"]
      Text[1, 2] chars:[1, 2, "a"]
      StrongEmphasis[2, 7] textOpen:[2, 4, "**"] text:[4, 5, "b"] textClose:[5, 7, "**"]
        Text[4, 5] chars:[4, 5, "b"]
      Text[7, 8] chars:[7, 8, "c"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 7
**a*b*c**

.
<p><strong>a<em>b</em>c</strong></p>
.
Document[0, 11]
  Paragraph[0, 10]
    StrongEmphasis[0, 9] textOpen:[0, 2, "**"] text:[2, 7, "a*b*c"] textClose:[7, 9, "**"]
      Text[2, 3] chars:[2, 3, "a"]
      Emphasis[3, 6] textOpen:[3, 4, "*"] text:[4, 5, "b"] textClose:[5, 6, "*"]
        Text[4, 5] chars:[4, 5, "b"]
      Text[6, 7] chars:[6, 7, "c"]
````````````````````````````````


more emphasis tests

```````````````````````````````` example GFM Emphasis: 8
**a b*b*b c**

.
<p><strong>a b<em>b</em>b c</strong></p>
.
Document[0, 15]
  Paragraph[0, 14]
    StrongEmphasis[0, 13] textOpen:[0, 2, "**"] text:[2, 11, "a b*b*b c"] textClose:[11, 13, "**"]
      Text[2, 5] chars:[2, 5, "a b"]
      Emphasis[5, 8] textOpen:[5, 6, "*"] text:[6, 7, "b"] textClose:[7, 8, "*"]
        Text[6, 7] chars:[6, 7, "b"]
      Text[8, 11] chars:[8, 11, "b c"]
````````````````````````````````


This works as expected:

```````````````````````````````` example GFM Emphasis: 9
**bold *bold-italic* bold**
.
<p><strong>bold <em>bold-italic</em> bold</strong></p>
.
Document[0, 28]
  Paragraph[0, 28]
    StrongEmphasis[0, 27] textOpen:[0, 2, "**"] text:[2, 25, "bold *bold-italic* bold"] textClose:[25, 27, "**"]
      Text[2, 7] chars:[2, 7, "bold "]
      Emphasis[7, 20] textOpen:[7, 8, "*"] text:[8, 19, "bold-italic"] textClose:[19, 20, "*"]
        Text[8, 19] chars:[8, 19, "bold- … talic"]
      Text[20, 25] chars:[20, 25, " bold"]
````````````````````````````````


code mixed with emphasis:

```````````````````````````````` example GFM Emphasis: 10
`code with `**`bold`**` inside`
.
<p><code>code with</code><strong><code>bold</code></strong><code>inside</code></p>
.
Document[0, 32]
  Paragraph[0, 32]
    Code[0, 12] textOpen:[0, 1, "`"] text:[1, 11, "code with "] textClose:[11, 12, "`"]
    StrongEmphasis[12, 22] textOpen:[12, 14, "**"] text:[14, 20, "`bold`"] textClose:[20, 22, "**"]
      Code[14, 20] textOpen:[14, 15, "`"] text:[15, 19, "bold"] textClose:[19, 20, "`"]
    Code[22, 31] textOpen:[22, 23, "`"] text:[23, 30, " inside"] textClose:[30, 31, "`"]
````````````````````````````````


## Fenced Code Options

Change language class prefix

```````````````````````````````` example(Fenced Code Options: 1) options(no-class-prefix)
```text
plain text
```
.
<pre><code class="text">plain text
</code></pre>
.
Document[0, 23]
  FencedCodeBlock[0, 22] open:[0, 3, "```"] info:[3, 7, "text"] content:[8, 19] lines[3] close:[19, 22, "```"]
````````````````````````````````


empty, no info

```````````````````````````````` example Fenced Code Options: 2
```

```
.
<pre><code>
</code></pre>
.
Document[0, 9]
  FencedCodeBlock[0, 8] open:[0, 3, "```"] content:[4, 5] lines[3] close:[5, 8, "```"]
````````````````````````````````


empty, no info, blank line follows

```````````````````````````````` example Fenced Code Options: 3
```

```

.
<pre><code>
</code></pre>
.
Document[0, 10]
  FencedCodeBlock[0, 8] open:[0, 3, "```"] content:[4, 5] lines[3] close:[5, 8, "```"]
````````````````````````````````


empty, info

```````````````````````````````` example Fenced Code Options: 4
```info

```
.
<pre><code class="language-info">
</code></pre>
.
Document[0, 13]
  FencedCodeBlock[0, 12] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 9] lines[3] close:[9, 12, "```"]
````````````````````````````````


empty, info, blank line follows

```````````````````````````````` example Fenced Code Options: 5
```info

```

.
<pre><code class="language-info">
</code></pre>
.
Document[0, 14]
  FencedCodeBlock[0, 12] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 9] lines[3] close:[9, 12, "```"]
````````````````````````````````


## Anchor links option

Change language class prefix

```````````````````````````````` example Anchor links option: 1
inline anchor <a id="test" href="#"></a><em></em> test 
.
<p>inline anchor <a id="test" href="#"></a><em></em> test</p>
.
Document[0, 56]
  Paragraph[0, 56]
    Text[0, 14] chars:[0, 14, "inlin … chor "]
    HtmlInline[14, 36] chars:[14, 36, "<a id … =\"#\">"]
    HtmlInline[36, 40] chars:[36, 40, "</a>"]
    HtmlInline[40, 44] chars:[40, 44, "<em>"]
    HtmlInline[44, 49] chars:[44, 49, "</em>"]
    Text[49, 54] chars:[49, 54, " test"]
````````````````````````````````


## Thematic Break

Break with trailing spaces

```````````````````````````````` example Thematic Break: 1
---                 
.
<hr />
.
Document[0, 21]
  ThematicBreak[0, 20]
````````````````````````````````


## Image links

```````````````````````````````` example Image links: 1
![alt](/url) 
.
<p><img src="/url" alt="alt" /></p>
.
Document[0, 14]
  Paragraph[0, 14]
    Image[0, 12] textOpen:[0, 2, "!["] text:[2, 5, "alt"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 11, "/url"] pageRef:[7, 11, "/url"] linkClose:[11, 12, ")"]
      Text[2, 5] chars:[2, 5, "alt"]
````````````````````````````````


dummy ref

```````````````````````````````` example Image links: 2
[ref]: /url1

![ref][]
.
<p><img src="/url1" alt="ref" /></p>
.
Document[0, 23]
  Reference[0, 12] refOpen:[0, 1, "["] ref:[1, 4, "ref"] refClose:[4, 6, "]:"] url:[7, 12, "/url1"]
  Paragraph[14, 23]
    ImageRef[14, 22] referenceOpen:[14, 16, "!["] reference:[16, 19, "ref"] referenceClose:[19, 20, "]"] textOpen:[20, 21, "["] textClose:[21, 22, "]"]
      Text[16, 19] chars:[16, 19, "ref"]
````````````````````````````````


## Fenced Code

Option not to match closing fence characters to opening ones

```````````````````````````````` example(Fenced Code: 1) options(unmatched-fence)
```
proper unmatched fenced code
~~~
.
<pre><code>proper unmatched fenced code
</code></pre>
.
Document[0, 37]
  FencedCodeBlock[0, 36] open:[0, 3, "```"] content:[4, 33] lines[3] close:[33, 36, "~~~"]
````````````````````````````````


```````````````````````````````` example(Fenced Code: 2) options(unmatched-fence)
~~~
proper unmatched fenced code
```
.
<pre><code>proper unmatched fenced code
</code></pre>
.
Document[0, 37]
  FencedCodeBlock[0, 36] open:[0, 3, "~~~"] content:[4, 33] lines[3] close:[33, 36, "```"]
````````````````````````````````


non empty, info, blank line follows, unmatched

```````````````````````````````` example(Fenced Code: 3) options(unmatched-fence)
```info
some text
~~~

.
<pre><code class="language-info">some text
</code></pre>
.
Document[0, 23]
  FencedCodeBlock[0, 21] open:[0, 3, "```"] info:[3, 7, "info"] content:[8, 18] lines[3] close:[18, 21, "~~~"]
````````````````````````````````


## IntelliJ Dummy Identifier 

allow dummy identifier in url and text

```````````````````````````````` example(IntelliJ Dummy Identifier: 1) options(dummy-identifier)
[⎮text](/ur⎮l)

![al⎮t](/url⎮)

.
<p><a href="/ur%1Fl">⎮text</a></p>
<p><img src="/url%1F" alt="al⎮t" /></p>
.
Document[0, 32]
  Paragraph[0, 15]
    Link[0, 14] textOpen:[0, 1, "["] text:[1, 6, "%1ftext"] textClose:[6, 7, "]"] linkOpen:[7, 8, "("] url:[8, 13, "/ur%1fl"] pageRef:[8, 13, "/ur%1fl"] linkClose:[13, 14, ")"]
      Text[1, 6] chars:[1, 6, "%1ftext"]
  Paragraph[16, 31]
    Image[16, 30] textOpen:[16, 18, "!["] text:[18, 22, "al%1ft"] textClose:[22, 23, "]"] linkOpen:[23, 24, "("] url:[24, 29, "/url%1f"] pageRef:[24, 29, "/url%1f"] linkClose:[29, 30, ")"]
      Text[18, 22] chars:[18, 22, "al%1ft"]
````````````````````````````````


## Indented Code Options 

no trim trailing blank lines

```````````````````````````````` example Indented Code Options: 1
    code
    code line
    
    
    
.
<pre><code>code
code line
</code></pre>
.
Document[0, 38]
  IndentedCodeBlock[4, 38]
````````````````````````````````


trim trailing blank lines

```````````````````````````````` example(Indented Code Options: 2) options(code-trim-trailing)
    code
    code line
    
    
    
.
<pre><code>code
code line
</code></pre>
.
Document[0, 38]
  IndentedCodeBlock[4, 23]
````````````````````````````````


## Links

Url encoded link address should not % encode the query separator `&`

```````````````````````````````` example Links: 1
[test](http://url?opt=a&opt1=b)
.
<p><a href="http://url?opt=a&amp;opt1=b">test</a></p>
.
Document[0, 32]
  Paragraph[0, 32]
    Link[0, 31] textOpen:[0, 1, "["] text:[1, 5, "test"] textClose:[5, 6, "]"] linkOpen:[6, 7, "("] url:[7, 30, "http://url?opt=a&opt1=b"] pageRef:[7, 30, "http://url?opt=a&opt1=b"] linkClose:[30, 31, ")"]
      Text[1, 5] chars:[1, 5, "test"]
````````````````````````````````


```````````````````````````````` example Links: 2
[ref]: /url1

[ref][]
.
<p><a href="/url1">ref</a></p>
.
Document[0, 22]
  Reference[0, 12] refOpen:[0, 1, "["] ref:[1, 4, "ref"] refClose:[4, 6, "]:"] url:[7, 12, "/url1"]
  Paragraph[14, 22]
    LinkRef[14, 21] referenceOpen:[14, 15, "["] reference:[15, 18, "ref"] referenceClose:[18, 19, "]"] textOpen:[19, 20, "["] textClose:[20, 21, "]"]
      Text[15, 18] chars:[15, 18, "ref"]
````````````````````````````````


## Block Quotes

Extend block quote to next blank line

```````````````````````````````` example(Block Quotes: 1) options(block-quote-extend)
> 1. one
2. two
.
<blockquote>
  <ol>
    <li>one</li>
    <li>two</li>
  </ol>
</blockquote>
.
Document[0, 16]
  BlockQuote[0, 16] marker:[0, 1, ">"]
    OrderedList[2, 16] isTight delimiter:'.'
      OrderedListItem[2, 9] open:[2, 4, "1."] isTight
        Paragraph[5, 9]
          Text[5, 8] chars:[5, 8, "one"]
      OrderedListItem[9, 16] open:[9, 11, "2."] isTight
        Paragraph[12, 16]
          Text[12, 15] chars:[12, 15, "two"]
````````````````````````````````


without block quote to next blank line causes an interrupted list with a second list after the
quote.

```````````````````````````````` example Block Quotes: 2
> 1. one
2. two
.
<blockquote>
  <ol>
    <li>one</li>
  </ol>
</blockquote>
<ol start="2">
  <li>two</li>
</ol>
.
Document[0, 16]
  BlockQuote[0, 9] marker:[0, 1, ">"]
    OrderedList[2, 9] isTight delimiter:'.'
      OrderedListItem[2, 9] open:[2, 4, "1."] isTight
        Paragraph[5, 9]
          Text[5, 8] chars:[5, 8, "one"]
  OrderedList[9, 16] isTight start:2 delimiter:'.'
    OrderedListItem[9, 16] open:[9, 11, "2."] isTight
      Paragraph[12, 16]
        Text[12, 15] chars:[12, 15, "two"]
````````````````````````````````


## Source Position Attribute

```````````````````````````````` example(Source Position Attribute: 1) options(src-pos)
<http://url> 
`code` 
_text_ 
![alt](/url) 
![img]
![img][]
[text](/url) 
[ref]
[ref][]
<name@mail.com>
**text**

---

# Heading

- item

<!-- -->

1. item

<!-- -->

    indented code
    

[img]: /img.png
[ref]: /url

    text

.
<p md-pos="0-113"><a md-pos="1-11" href="http://url">http://url</a>
<code md-pos="15-19">code</code>
<em md-pos="23-27">text</em>
<img src="/url" alt="alt" md-pos="30-42" />
<img src="/img.png" alt="img" md-pos="44-50" />
<img src="/img.png" alt="img" md-pos="51-59" />
<a href="/url" md-pos="60-72">text</a>
<a href="/url" md-pos="74-79">ref</a>
<a href="/url" md-pos="80-87">ref</a>
<a md-pos="89-102" href="mailto:name@mail.com">name@mail.com</a>
<strong md-pos="106-110">text</strong></p>
<hr md-pos="114-117" />
<h1 md-pos="121-128">Heading</h1>
<ul>
  <li md-pos="130-137">item</li>
</ul>
<!-- -->
<ol>
  <li md-pos="148-156">item</li>
</ol>
<!-- -->
<pre md-pos="171-191"><code md-pos="171-191">indented code
</code></pre>
<pre md-pos="224-230"><code md-pos="224-230">text
</code></pre>
.
Document[0, 230]
  Paragraph[0, 113]
    AutoLink[0, 12] textOpen:[0, 1, "<"] text:[1, 11, "http://url"] textClose:[11, 12, ">"]
    SoftLineBreak[13, 14]
    Code[14, 20] textOpen:[14, 15, "`"] text:[15, 19, "code"] textClose:[19, 20, "`"]
    SoftLineBreak[21, 22]
    Emphasis[22, 28] textOpen:[22, 23, "_"] text:[23, 27, "text"] textClose:[27, 28, "_"]
      Text[23, 27] chars:[23, 27, "text"]
    SoftLineBreak[29, 30]
    Image[30, 42] textOpen:[30, 32, "!["] text:[32, 35, "alt"] textClose:[35, 36, "]"] linkOpen:[36, 37, "("] url:[37, 41, "/url"] pageRef:[37, 41, "/url"] linkClose:[41, 42, ")"]
      Text[32, 35] chars:[32, 35, "alt"]
    SoftLineBreak[43, 44]
    ImageRef[44, 50] referenceOpen:[44, 46, "!["] reference:[46, 49, "img"] referenceClose:[49, 50, "]"]
      Text[46, 49] chars:[46, 49, "img"]
    SoftLineBreak[50, 51]
    ImageRef[51, 59] referenceOpen:[51, 53, "!["] reference:[53, 56, "img"] referenceClose:[56, 57, "]"] textOpen:[57, 58, "["] textClose:[58, 59, "]"]
      Text[53, 56] chars:[53, 56, "img"]
    SoftLineBreak[59, 60]
    Link[60, 72] textOpen:[60, 61, "["] text:[61, 65, "text"] textClose:[65, 66, "]"] linkOpen:[66, 67, "("] url:[67, 71, "/url"] pageRef:[67, 71, "/url"] linkClose:[71, 72, ")"]
      Text[61, 65] chars:[61, 65, "text"]
    SoftLineBreak[73, 74]
    LinkRef[74, 79] referenceOpen:[74, 75, "["] reference:[75, 78, "ref"] referenceClose:[78, 79, "]"]
      Text[75, 78] chars:[75, 78, "ref"]
    SoftLineBreak[79, 80]
    LinkRef[80, 87] referenceOpen:[80, 81, "["] reference:[81, 84, "ref"] referenceClose:[84, 85, "]"] textOpen:[85, 86, "["] textClose:[86, 87, "]"]
      Text[81, 84] chars:[81, 84, "ref"]
    SoftLineBreak[87, 88]
    MailLink[88, 103] textOpen:[88, 89, "<"] text:[89, 102, "name@mail.com"] textClose:[102, 103, ">"]
    SoftLineBreak[103, 104]
    StrongEmphasis[104, 112] textOpen:[104, 106, "**"] text:[106, 110, "text"] textClose:[110, 112, "**"]
      Text[106, 110] chars:[106, 110, "text"]
  ThematicBreak[114, 117]
  Heading[119, 128] textOpen:[119, 120, "#"] text:[121, 128, "Heading"]
    Text[121, 128] chars:[121, 128, "Heading"]
  BulletList[130, 137] isTight
    BulletListItem[130, 137] open:[130, 131, "-"] isTight
      Paragraph[132, 137]
        Text[132, 136] chars:[132, 136, "item"]
  HtmlCommentBlock[138, 147]
  OrderedList[148, 156] isTight delimiter:'.'
    OrderedListItem[148, 156] open:[148, 150, "1."] isTight
      Paragraph[151, 156]
        Text[151, 155] chars:[151, 155, "item"]
  HtmlCommentBlock[157, 166]
  IndentedCodeBlock[171, 191]
  Reference[191, 206] refOpen:[191, 192, "["] ref:[192, 195, "img"] refClose:[195, 197, "]:"] url:[198, 206, "/img.png"]
  Reference[207, 218] refOpen:[207, 208, "["] ref:[208, 211, "ref"] refClose:[211, 213, "]:"] url:[214, 218, "/url"]
  IndentedCodeBlock[224, 230]
````````````````````````````````


fenced code

```````````````````````````````` example(Source Position Attribute: 2) options(src-pos)
```text
text
```

.
<pre md-pos="0-17"><code class="language-text" md-pos="8-13">text
</code></pre>
.
Document[0, 18]
  FencedCodeBlock[0, 16] open:[0, 3, "```"] info:[3, 7, "text"] content:[8, 13] lines[3] close:[13, 16, "```"]
````````````````````````````````


fenced code with trailing spaces and tabs on close

```````````````````````````````` example(Source Position Attribute: 3) options(src-pos)
```text
text
``` →

test
.
<pre md-pos="0-19"><code class="language-text" md-pos="8-13">text
</code></pre>
<p md-pos="20-25">test</p>
.
Document[0, 25]
  FencedCodeBlock[0, 16] open:[0, 3, "```"] info:[3, 7, "text"] content:[8, 13] lines[3] close:[13, 16, "```"]
  Paragraph[20, 25]
    Text[20, 24] chars:[20, 24, "test"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans

```````````````````````````````` example(Source Position Attribute: 4) options(src-pos, src-pos-lines)
paragraph test 
with multiple lazy lines
all should be src pos wrapped

- item
with multiple lazy lines
all should be src pos wrapped

<!-- -->

- item
with multiple lazy lines
all should be src pos wrapped

- item
<!-- -->

1. item
with multiple lazy lines
all should be src pos wrapped

1. item

<!-- -->

1. item
with multiple lazy lines
all should be src pos wrapped
1. item

<!-- -->

- [ ] item
with multiple lazy lines
all should be src pos wrapped
.
<p md-pos="0-71"><span md-pos="0-14">paragraph test</span>
<span md-pos="16-40">with multiple lazy lines</span>
<span md-pos="41-70">all should be src pos wrapped</span></p>
<ul>
  <li md-pos="72-134"><span md-pos="74-78">item</span>
  <span md-pos="79-103">with multiple lazy lines</span>
  <span md-pos="104-133">all should be src pos wrapped</span></li>
</ul>
<!-- -->
<ul>
  <li md-pos="145-207">
    <p md-pos="147-207"><span md-pos="147-151">item</span>
    <span md-pos="152-176">with multiple lazy lines</span>
    <span md-pos="177-206">all should be src pos wrapped</span></p>
  </li>
  <li md-pos="208-215">
    <p md-pos="210-215"><span md-pos="210-214">item</span></p>
  </li>
</ul>
<!-- -->
<ol>
  <li md-pos="225-288">
    <p md-pos="228-288"><span md-pos="228-232">item</span>
    <span md-pos="233-257">with multiple lazy lines</span>
    <span md-pos="258-287">all should be src pos wrapped</span></p>
  </li>
  <li md-pos="289-297">
    <p md-pos="292-297"><span md-pos="292-296">item</span></p>
  </li>
</ol>
<!-- -->
<ol>
  <li md-pos="308-371"><span md-pos="311-315">item</span>
  <span md-pos="316-340">with multiple lazy lines</span>
  <span md-pos="341-370">all should be src pos wrapped</span></li>
  <li md-pos="371-379"><span md-pos="374-378">item</span></li>
</ol>
<!-- -->
<ul>
  <li md-pos="390-456"><span md-pos="392-400">[ ] item</span>
  <span md-pos="401-425">with multiple lazy lines</span>
  <span md-pos="426-455">all should be src pos wrapped</span></li>
</ul>
.
Document[0, 456]
  Paragraph[0, 71]
    Text[0, 14] chars:[0, 14, "parag …  test"]
    SoftLineBreak[15, 16]
    Text[16, 40] chars:[16, 40, "with  … lines"]
    SoftLineBreak[40, 41]
    Text[41, 70] chars:[41, 70, "all s … apped"]
  BulletList[72, 134] isTight
    BulletListItem[72, 134] open:[72, 73, "-"] isTight
      Paragraph[74, 134]
        Text[74, 78] chars:[74, 78, "item"]
        SoftLineBreak[78, 79]
        Text[79, 103] chars:[79, 103, "with  … lines"]
        SoftLineBreak[103, 104]
        Text[104, 133] chars:[104, 133, "all s … apped"]
  HtmlCommentBlock[135, 144]
  BulletList[145, 215] isLoose
    BulletListItem[145, 207] open:[145, 146, "-"] isLoose
      Paragraph[147, 207]
        Text[147, 151] chars:[147, 151, "item"]
        SoftLineBreak[151, 152]
        Text[152, 176] chars:[152, 176, "with  … lines"]
        SoftLineBreak[176, 177]
        Text[177, 206] chars:[177, 206, "all s … apped"]
    BulletListItem[208, 215] open:[208, 209, "-"] isLoose
      Paragraph[210, 215]
        Text[210, 214] chars:[210, 214, "item"]
  HtmlCommentBlock[215, 224]
  OrderedList[225, 297] isLoose delimiter:'.'
    OrderedListItem[225, 288] open:[225, 227, "1."] isLoose
      Paragraph[228, 288]
        Text[228, 232] chars:[228, 232, "item"]
        SoftLineBreak[232, 233]
        Text[233, 257] chars:[233, 257, "with  … lines"]
        SoftLineBreak[257, 258]
        Text[258, 287] chars:[258, 287, "all s … apped"]
    OrderedListItem[289, 297] open:[289, 291, "1."] isLoose
      Paragraph[292, 297]
        Text[292, 296] chars:[292, 296, "item"]
  HtmlCommentBlock[298, 307]
  OrderedList[308, 379] isTight delimiter:'.'
    OrderedListItem[308, 371] open:[308, 310, "1."] isTight
      Paragraph[311, 371]
        Text[311, 315] chars:[311, 315, "item"]
        SoftLineBreak[315, 316]
        Text[316, 340] chars:[316, 340, "with  … lines"]
        SoftLineBreak[340, 341]
        Text[341, 370] chars:[341, 370, "all s … apped"]
    OrderedListItem[371, 379] open:[371, 373, "1."] isTight
      Paragraph[374, 379]
        Text[374, 378] chars:[374, 378, "item"]
  HtmlCommentBlock[380, 389]
  BulletList[390, 456] isTight
    BulletListItem[390, 456] open:[390, 391, "-"] isTight
      Paragraph[392, 456]
        LinkRef[392, 395] referenceOpen:[392, 393, "["] reference:[394, 394] referenceClose:[394, 395, "]"]
          Text[393, 394] chars:[393, 394, " "]
        Text[395, 400] chars:[395, 400, " item"]
        SoftLineBreak[400, 401]
        Text[401, 425] chars:[401, 425, "with  … lines"]
        SoftLineBreak[425, 426]
        Text[426, 455] chars:[426, 455, "all s … apped"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans

```````````````````````````````` example(Source Position Attribute: 5) options(src-pos, src-pos-lines)
paragraph test 
with multiple lazy lines
all should be src pos wrapped
.
<p md-pos="0-71"><span md-pos="0-14">paragraph test</span>
<span md-pos="16-40">with multiple lazy lines</span>
<span md-pos="41-70">all should be src pos wrapped</span></p>
.
Document[0, 71]
  Paragraph[0, 71]
    Text[0, 14] chars:[0, 14, "parag …  test"]
    SoftLineBreak[15, 16]
    Text[16, 40] chars:[16, 40, "with  … lines"]
    SoftLineBreak[40, 41]
    Text[41, 70] chars:[41, 70, "all s … apped"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans tight list items

```````````````````````````````` example(Source Position Attribute: 6) options(src-pos, src-pos-lines)
- item
with multiple lazy lines
all should be src pos wrapped

<!-- -->

1. item
with multiple lazy lines
all should be src pos wrapped

.
<ul>
  <li md-pos="0-62"><span md-pos="2-6">item</span>
  <span md-pos="7-31">with multiple lazy lines</span>
  <span md-pos="32-61">all should be src pos wrapped</span></li>
</ul>
<!-- -->
<ol>
  <li md-pos="73-136"><span md-pos="76-80">item</span>
  <span md-pos="81-105">with multiple lazy lines</span>
  <span md-pos="106-135">all should be src pos wrapped</span></li>
</ol>
.
Document[0, 137]
  BulletList[0, 62] isTight
    BulletListItem[0, 62] open:[0, 1, "-"] isTight
      Paragraph[2, 62]
        Text[2, 6] chars:[2, 6, "item"]
        SoftLineBreak[6, 7]
        Text[7, 31] chars:[7, 31, "with  … lines"]
        SoftLineBreak[31, 32]
        Text[32, 61] chars:[32, 61, "all s … apped"]
  HtmlCommentBlock[63, 72]
  OrderedList[73, 136] isTight delimiter:'.'
    OrderedListItem[73, 136] open:[73, 75, "1."] isTight
      Paragraph[76, 136]
        Text[76, 80] chars:[76, 80, "item"]
        SoftLineBreak[80, 81]
        Text[81, 105] chars:[81, 105, "with  … lines"]
        SoftLineBreak[105, 106]
        Text[106, 135] chars:[106, 135, "all s … apped"]
````````````````````````````````


Wrap individual paragraph lines in source position marked spans loose list items

```````````````````````````````` example(Source Position Attribute: 7) options(src-pos, src-pos-lines)
- item

- item
with multiple lazy lines
all should be src pos wrapped

<!-- -->

1. item

1. item
with multiple lazy lines
all should be src pos wrapped

.
<ul>
  <li md-pos="0-7">
    <p md-pos="2-7"><span md-pos="2-6">item</span></p>
  </li>
  <li md-pos="8-70">
    <p md-pos="10-70"><span md-pos="10-14">item</span>
    <span md-pos="15-39">with multiple lazy lines</span>
    <span md-pos="40-69">all should be src pos wrapped</span></p>
  </li>
</ul>
<!-- -->
<ol>
  <li md-pos="81-89">
    <p md-pos="84-89"><span md-pos="84-88">item</span></p>
  </li>
  <li md-pos="90-153">
    <p md-pos="93-153"><span md-pos="93-97">item</span>
    <span md-pos="98-122">with multiple lazy lines</span>
    <span md-pos="123-152">all should be src pos wrapped</span></p>
  </li>
</ol>
.
Document[0, 154]
  BulletList[0, 70] isLoose
    BulletListItem[0, 7] open:[0, 1, "-"] isLoose
      Paragraph[2, 7]
        Text[2, 6] chars:[2, 6, "item"]
    BulletListItem[8, 70] open:[8, 9, "-"] isLoose
      Paragraph[10, 70]
        Text[10, 14] chars:[10, 14, "item"]
        SoftLineBreak[14, 15]
        Text[15, 39] chars:[15, 39, "with  … lines"]
        SoftLineBreak[39, 40]
        Text[40, 69] chars:[40, 69, "all s … apped"]
  HtmlCommentBlock[71, 80]
  OrderedList[81, 153] isLoose delimiter:'.'
    OrderedListItem[81, 89] open:[81, 83, "1."] isLoose
      Paragraph[84, 89]
        Text[84, 88] chars:[84, 88, "item"]
    OrderedListItem[90, 153] open:[90, 92, "1."] isLoose
      Paragraph[93, 153]
        Text[93, 97] chars:[93, 97, "item"]
        SoftLineBreak[97, 98]
        Text[98, 122] chars:[98, 122, "with  … lines"]
        SoftLineBreak[122, 123]
        Text[123, 152] chars:[123, 152, "all s … apped"]
````````````````````````````````


