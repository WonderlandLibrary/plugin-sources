package fr.body.ectasy.script;

import org.luaj.vm2.Globals;
import org.luaj.vm2.lib.jse.JsePlatform;

public class ScriptParser {
   Globals globals = JsePlatform.standardGlobals();

   public ScriptParser(String var1, String var2) {
      this.globals.load(var2).call();
      if (this.globals.get("name") == null) {
         Logger.fatal("Script \"" + var1 + "\" has no name.");
      } else if (this.globals.get("description") == null) {
         Logger.fatal("Script \"" + var1 + "\" has no description.");
      } else if (this.globals.get("version") == null) {
         Logger.fatal("Script \"" + var1 + "\" has no version.");
      } else if (this.globals.get("blatant") == null) {
         Logger.fatal("Script \"" + var1 + "\" has no blatant field.");
      } else {
         Logger.info(
            "Initialized "
               + this.globals.get("name")
               + " v"
               + this.globals.get("version")
               + ", desc: "
               + this.globals.get("description")
               + ", blatant: "
               + this.globals.get(this.globals)
         );
      }
   }
}
