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
package org.dataconservancy.ui.services;

import java.util.List;

import org.dataconservancy.ui.exceptions.PackageException;
import org.dataconservancy.ui.model.Package;

/**
 * {@code PackageService} provides supports for Create-Retrieve-Update-Delete operations on {@link Package} objects.
 */
public interface PackageService {

    public Package get(String id);

    public Package create(Package pkg) throws PackageException;

    public Package update(Package pkg) throws PackageException;
    
    public void remove(Package pkg);

    public List<Package> findAllPackages();
}
