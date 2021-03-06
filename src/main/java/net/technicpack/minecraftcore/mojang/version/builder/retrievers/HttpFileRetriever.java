/*
 * This file is part of Technic Minecraft Core.
 * Copyright (C) 2013 Syndicate, LLC
 *
 * Technic Minecraft Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Technic Minecraft Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * as well as a copy of the GNU Lesser General Public License,
 * along with Technic Minecraft Core.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.technicpack.minecraftcore.mojang.version.builder.retrievers;

import net.technicpack.launchercore.install.verifiers.ValidJsonFileVerifier;
import net.technicpack.launchercore.mirror.MirrorStore;
import net.technicpack.launchercore.util.DownloadListener;
import net.technicpack.minecraftcore.MojangUtils;
import net.technicpack.minecraftcore.mojang.version.builder.MojangVersionRetriever;

import java.io.File;
import java.io.IOException;

public class HttpFileRetriever implements MojangVersionRetriever {

    private MirrorStore mirrorStore;
    private String baseUrl;
    private DownloadListener listener;

    public HttpFileRetriever(MirrorStore mirrorStore, String baseUrl, DownloadListener listener) {
        this.mirrorStore = mirrorStore;
        this.baseUrl = baseUrl;
        this.listener = listener;
    }

    @Override
    public void retrieveVersion(File target, String key) throws InterruptedException, IOException {
        String url = baseUrl + key + "/" + key + ".json";
        mirrorStore.downloadFile(url, target.getName(), target.getAbsolutePath(), null, new ValidJsonFileVerifier(MojangUtils.getGson()), listener);
    }
}
