/*
 * Copyright 2012 Johns Hopkins University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dataconservancy.index.gqmpsql;

import org.dataconservancy.model.gqm.Geometry;
import org.dataconservancy.model.gqm.Point;

public class PostgisUtil {
    public static String convertGeometryToEwkt(Geometry g, int srid) {
        StringBuilder sb = new StringBuilder();

        if (srid != -1) {
            sb.append("SRID=");
            sb.append(srid);
            sb.append(';');
        }

        if (g.getType() == Geometry.Type.POINT) {
            sb.append("POINT");
        } else if (g.getType() == Geometry.Type.LINE) {
            sb.append("LINESTRING");
        } else if (g.getType() == Geometry.Type.POLYGON) {
            sb.append("POLYGON");
        } else {
            throw new IllegalArgumentException("Unhandled geomtry type: "
                    + g.getType());
        }

        sb.append('(');

        if (g.getType() == Geometry.Type.POLYGON) {
            sb.append('(');
        }

        Point[] points = g.getPoints();

        for (int i = 0; i < points.length; i++) {
            if (i > 0) {
                sb.append(',');
            }

            append_point(sb, points[i]);
        }

        if (g.getType() == Geometry.Type.POLYGON) {
            // For polygon also add in first point to close it off

            if (points.length > 0) {
                sb.append(',');
                append_point(sb, points[0]);
            }

            sb.append(')');
        }

        sb.append(')');

        return sb.toString();
    }

    private static void append_point(StringBuilder sb, Point p) {
        double[] coords = p.getCoordinates();

        for (int i = 0; i < coords.length; i++) {
            if (i > 0) {
                sb.append(' ');
            }

            sb.append(coords[i]);
        }
    }
}
