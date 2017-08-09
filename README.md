# receipt-java [![Build Status](https://travis-ci.org/trojanc/receipt-java.svg?branch=master)](https://travis-ci.org/trojanc/receipt-java)



## Overview
<pre>
    ContextDefinition      ContextObject
            \                   /
             \                 /
                ContextResolver
                      /
                    /
           ContextMap      PrintTemplate
                \                 /
                 \               /
                 TemplateProcessor
                        /
                       /
           ProcessedTemplate    Printer -> LayoutBuilder
                 \                /
                  \              /
                   PrinterService
</pre>

