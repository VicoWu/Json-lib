/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.json.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Andres Almiray <aalmiray@users.sourceforge.net>
 */
public class CompositeJsonPropertyFilter implements JsonPropertyFilter {
   private List filters = new ArrayList();

   public CompositeJsonPropertyFilter() {
      this( null );
   }

   public CompositeJsonPropertyFilter( List filters ) {
      if( filters != null ){
         for( Iterator i = filters.iterator(); i.hasNext(); ){
            Object filter = i.next();
            if( filter instanceof JsonPropertyFilter ){
               this.filters.add( filter );
            }
         }
      }
   }

   public void addJsonPropertyFilter( JsonPropertyFilter filter ) {
      if( filter != null ){
         filters.add( filter );
      }
   }

   public boolean apply( Object source, String name, Object value ) {
      for( Iterator i = filters.iterator(); i.hasNext(); ){
         JsonPropertyFilter filter = (JsonPropertyFilter) i.next();
         if( filter.apply( source, name, value ) ){
            return true;
         }
      }
      return false;
   }

   public void removeJsonPropertyFilter( JsonPropertyFilter filter ) {
      if( filter != null ){
         filters.remove( filter );
      }
   }
}