stonemason



# Overview
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

