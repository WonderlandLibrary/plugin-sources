package fr.body.ectasy.user;

public enum Rank {
   UNAUTHORIZED(0),
   FREE(1),
   LITE(2),
   PREMIUM(3),
   DEVELOPER(4),
   ADMIN(5);

   public final int index;

   Rank(int index) {
      this.index = index;
   }
}
