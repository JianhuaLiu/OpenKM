/**
 *  OpenKM, Open Document Management System (http://www.openkm.com)
 *  Copyright (c) 2006-2014  Paco Avila & Josep Llort
 *
 *  No bytes were intentionally harmed during the development of this application.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.openkm.util.impexp;

import java.io.File;

public class TextInfoDecorator implements InfoDecorator {
	private int cut;
	
	public TextInfoDecorator(File tmp) {
		this.cut = tmp.getPath().length()+1;
	}
	
	public TextInfoDecorator(String path) {
		this.cut = path.length()+1;
	}
	
	@Override
	public String print(String path, long size, String error) {
		StringBuffer sb = new StringBuffer();
		
		if (error != null) {
			sb.append(path.substring(cut));
			sb.append(" : ");
			sb.append(error);
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
