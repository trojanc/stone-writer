#stone-writer [![Build Status](https://travis-ci.org/trojanc/stone-writer.svg?branch=master)](https://travis-ci.org/trojanc/stone-writer)



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

